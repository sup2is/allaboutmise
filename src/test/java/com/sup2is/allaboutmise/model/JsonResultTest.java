package com.sup2is.allaboutmise.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonResultTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testJsonResult() throws IOException {
		JsonResult jsonResult = new JsonResult();
		String json = objectMapper.writeValueAsString(jsonResult);
		JsonNode node = objectMapper.readTree(json);
		String result = node.get("result").asText();
		assertEquals("SUCCESS", result);
	}

	@Test
	public void testJsonResult_withParam() throws IOException {
		
		Map<String, Object> map = new HashMap<>();
		map.put("this is", "test");
		JsonResult jsonResult = new JsonResult(map);
		String json = objectMapper.writeValueAsString(jsonResult);
		JsonNode node = objectMapper.readTree(json);
		String result = node.get("result").asText();
		assertEquals("SUCCESS", result);
	}
	
	@Test
	public void testJsonResult_withException() throws IOException{
		JsonResult jsonResult = null;
		jsonResult = new JsonResult(new Exception("에러발생"));
		String json = objectMapper.writeValueAsString(jsonResult);
		JsonNode node = objectMapper.readTree(json);
		String result = node.get("result").asText();
		assertEquals("FAILED", result);
	}
}
