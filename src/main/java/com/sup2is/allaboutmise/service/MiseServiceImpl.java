package com.sup2is.allaboutmise.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MiseServiceImpl implements MiseService {

	@Override
	@Cacheable(value = "mise", key="#cityName")
	public void getCachedMiseList(String cityName) {
		
	}
	
}
