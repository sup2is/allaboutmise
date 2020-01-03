package com.sup2is.allaboutmise.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sup2is.allaboutmise.model.Mise;

import net.sf.ehcache.CacheManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiseServiceTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MiseService miseService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Test
	public void 실시간미세먼지데이터() throws Exception {
		//given
		String cityName = "서울";

		//when
		List<Mise> miseList = miseService.getCachedRealTimeMiseListByCityName(cityName);

		//then
		assertEquals(true, miseList.size() > 0);
		System.out.println(miseList.toString());
	}
	
	
	@Test
	public void 실시간미세먼지데이터캐싱() throws Exception {
		//given
		String cityName = "인천";
		
		//when
		List<Mise> miseList = miseService.getCachedRealTimeMiseListByCityName(cityName);
		
		//then
		Element cached = cacheManager.getCache("mise-realtime").get("인천");
		assertNotNull(cached);
		System.out.println(cached.toString());
	}
}
