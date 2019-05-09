package com.sup2is.allaboutmise.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Slf4j
@Component
public class GlobalTime {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private Environment environment;
	
	public int getReloadTimeToSeconds(String city) {
		Element cache = cacheManager.getCache("mise-realtime").get(city);
		long expiration = cache.getExpirationTime();
		long currentTime = System.currentTimeMillis();
		int reloadTime = (int) (Integer.parseInt(environment.getProperty("reload-time")) - (expiration - currentTime) /1000);
		log.debug("#### : reloadTime = " + reloadTime);
		return reloadTime;
	}
}
