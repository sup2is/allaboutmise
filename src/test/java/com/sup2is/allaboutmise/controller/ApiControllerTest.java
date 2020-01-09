package com.sup2is.allaboutmise.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sup2is.allaboutmise.util.GlobalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
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

	@Mock
	private GlobalTime globalTime;

	@InjectMocks
	private ApiController apiController;
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private Environment environment;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
	}
	
	@Test
	public void 도시목록가져오기() throws Exception {

		//given

		//when
		MvcResult result = this.mockMvc.perform(get("/api/cities"))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();

		//when
		String body = result.getResponse().getContentAsString();
		System.out.println(body);
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String,Object>>() {};
		Map<String, Object> map = objectMapper.readValue(body,typeRef);
		assertEquals("SUCCESS", map.get("result"));
		assertNotNull(map.get("param"));
		
	}

	@Test
	public void 모드가져오기() throws Exception {

		//given

		//when
		MvcResult result = this.mockMvc.perform(get("/api/modes"))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();

		//when
		String body = result.getResponse().getContentAsString();
		System.out.println(body);
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String,Object>>() {};
		Map<String, Object> map = objectMapper.readValue(body,typeRef);
		assertEquals("SUCCESS", map.get("result"));
		assertNotNull(map.get("param"));

	}

	@Test
	public void 미세먼지데이터가져오기() throws Exception {
		//given
		Map<String, String> data = new HashMap<String, String>();
		data.put("city", "인천");

		String jsonStr = objectMapper.writeValueAsString(data);
		
		//when
		MvcResult result = this.mockMvc.perform(post("/api/realtime-mise")
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
		assertEquals("SUCCESS", map.get("result"));

	}
}


