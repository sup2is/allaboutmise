package com.sup2is.allaboutmise.service;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiseServiceTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void testGetCachedMise() {
		
		StringBuilder url = new StringBuilder();

		url.append("http://openapi.airkorea.or.kr/openapi/services/rest");
		url.append("/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?");
		url.append("serviceKey=MubOZEkvZH1%2B8M98%2Fpbiml8OcBc%2B3We3nPNEBEauiZGN%2Br2HBWlDFedaf1w3Z%2BUe4LtZs3pn5%2FQylEZM4hsytg%3D%3D");
		url.append("&numOfRows=10");
		url.append("&pageNo=1");
		url.append("&sidoName=%EC%9D%B8%EC%B2%9C");
		url.append("&ver=1.3");
		
		System.out.println(url.toString());
		
		ResponseEntity<String> response = restTemplate.getForEntity("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=JjG0s4ycsYdZgdhyA6TSK0BmHZkjjF7Wl52jtIdrI4V%2BifddyGx1ETGSj%2BkK%2BfWdrYjW%2F1L%2B4hK0i0%2BA4viz4Q%3D%3D&numOfRows=1000&pageNo=1&sidoName=%EC%84%9C%EC%9A%B8&ver=1.3", String.class);

		System.out.println(response);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	public void testAPI() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("moTZa1hjRyRMkknu5gsGMte8uMO6W0EY7gLu8oayUtnwdFGb1PByAAdLZ1GNkYDvI%2FChqIfeBNXhI5oHxgoAIA%3D%3D","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("인천", "UTF-8")); /*시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.3", "UTF-8")); /*버전별 상세 결과 참고문서 참조*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
	}
	
}
