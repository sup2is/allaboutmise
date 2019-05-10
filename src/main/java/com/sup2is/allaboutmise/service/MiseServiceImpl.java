package com.sup2is.allaboutmise.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.SAXParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.util.XmlParserHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MiseServiceImpl implements MiseService {
	
	@Autowired
	private XmlParserHandler xmlParserHandler;
	
	@Autowired
	private SAXParser parser;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Cacheable(value = "mise-realtime", key="#cityName")
	public List<Mise> getCachedRealTimeMiseListByCityName(String cityName) throws Exception {
		ResponseEntity<String> response = getRealTimeMiseData(cityName);
		InputStream is = new ByteArrayInputStream(response.getBody().getBytes());
		parser.parse(is, xmlParserHandler);
		List<Mise> miseList = xmlParserHandler.getParsedData();
		System.out.println(miseList);
		return miseList;
	}

	private ResponseEntity<String> getRealTimeMiseData(String cityName) throws UnsupportedEncodingException, MalformedURLException, RestClientException, URISyntaxException {
		
		log.debug("### : api call!!!!");
		StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=JjG0s4ycsYdZgdhyA6TSK0BmHZkjjF7Wl52jtIdrI4V%2BifddyGx1ETGSj%2BkK%2BfWdrYjW%2F1L%2B4hK0i0%2BA4viz4Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode(cityName, "UTF-8")); /*시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.3", "UTF-8")); /*버전별 상세 결과 참고문서 참조*/
        URL url = new URL(urlBuilder.toString());
		return restTemplate.getForEntity(url.toURI(), String.class);
	}
	
}
