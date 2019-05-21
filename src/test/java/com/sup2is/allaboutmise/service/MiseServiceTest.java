package com.sup2is.allaboutmise.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

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
	public void testGetCachedMiseListByCityName_withData() throws Exception {
		
		//given
		String cityName = "서울";
		
		//when
		List<Mise> miseList = miseService.getCachedRealTimeMiseListByCityName(cityName);
		
		//then
		assertEquals(true, miseList.size() > 0);
		
		System.out.println(miseList.toString());
		cacheManager.getCache("mise").get(cityName);
		
	}
	
	
	@Test
	public void testGetCachedMiseListByCityName_cacheTest() throws Exception {
		
		//given
		String cityName = "인천";
		
		//when
		List<Mise> miseList = miseService.getCachedRealTimeMiseListByCityName(cityName);
		
		System.out.println(miseList);
		
		//then
		assertEquals(true, miseList.size() > 0);

		miseList = miseService.getCachedRealTimeMiseListByCityName(cityName);
		
		System.out.println(miseList);
		
	}
	
	
	
	

	@Test
	public void testAPI() throws IOException, RestClientException, URISyntaxException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=JjG0s4ycsYdZgdhyA6TSK0BmHZkjjF7Wl52jtIdrI4V%2BifddyGx1ETGSj%2BkK%2BfWdrYjW%2F1L%2B4hK0i0%2BA4viz4Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("인천", "UTF-8")); /*시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.3", "UTF-8")); /*버전별 상세 결과 참고문서 참조*/
        URL url = new URL(urlBuilder.toString());
		ResponseEntity<String> response = restTemplate.getForEntity(url.toURI(), String.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
