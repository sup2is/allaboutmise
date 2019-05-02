package com.sup2is.allaboutmise.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.service.MiseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerTest {

	@Mock
	private MiseService miseService;
	
	@InjectMocks
	private ApiController apiController;
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
	}
	
	@Test
	public void getGlobalTime() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/api/globalTime"))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
		
		String body = result.getResponse().getContentAsString();
		
		System.out.println(body);
		
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String,Object>>() {};
		
		Map<String, Object> map = objectMapper.readValue(body,typeRef);
		
		assertEquals("SUCCESS", map.get("result"));
		
	}
	
	@Test
	public void testRealtimeMise() throws Exception {
		//given
		Map<String, String> data = new HashMap<String, String>();
		data.put("city", "인천");

		String jsonStr = objectMapper.writeValueAsString(data);
		
		//when
		MvcResult result = this.mockMvc.perform(post("/api/realtime-mise/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		//then
		String body = result.getResponse().getContentAsString();
		
		System.out.println(body);
		
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String,Object>>() {};
		
		Map<String, Object> map = objectMapper.readValue(body,typeRef);
		
		Map<String, Object> param = (Map<String, Object>) map.get("param");
		
		List<Mise> miseList = (List<Mise>) param.get("miseList");
		assertEquals(true, miseList.size() > 0);
		
		
		
	}
}


