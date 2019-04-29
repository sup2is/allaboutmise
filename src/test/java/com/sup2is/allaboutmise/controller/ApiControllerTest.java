package com.sup2is.allaboutmise.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ApiController.class).build();
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
}


