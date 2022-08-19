package com.my.control;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BoardControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	void testBoardlistTest() throws Exception {
		MockHttpServletRequestBuilder mockRequestBuilder =
				MockMvcRequestBuilders.get("/boardlist").accept(org.springframework.http.MediaType.APPLICATION_JSON);
	
		int expextedStatus = 1;
		
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
	
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	
		org.hamcrest.Matcher<Integer> matcher;
		ResultMatcher resultMatcher;
		
		matcher = org.hamcrest.CoreMatchers.is(expextedStatus);
		resultMatcher = jsonPath("status", matcher);
		resultActions.andExpect(resultMatcher);
	}
	
	@Test
	void testSearchBoardTest() throws Exception {
		MockHttpServletRequestBuilder mockRequestBuilder =
				MockMvcRequestBuilders.get("/searchboard").accept(org.springframework.http.MediaType.APPLICATION_JSON);
				
		int expextedStatus = 1;	
		
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	
		org.hamcrest.Matcher<Integer> matcher;
		ResultMatcher resultMatcher;
		
		matcher = org.hamcrest.CoreMatchers.is(expextedStatus);
		resultMatcher = jsonPath("status", matcher);
		resultActions.andExpect(resultMatcher);
	}
	
	@Test
	void testWriteBoardTest() throws Exception {
		MockHttpServletRequestBuilder mockRequestBuilder =
				MockMvcRequestBuilders.post("/writeboard").accept(org.springframework.http.MediaType.APPLICATION_JSON);
		
	
		
		int expextedStatus = 1;	
		
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	
		org.hamcrest.Matcher<Integer> matcher;
		ResultMatcher resultMatcher;
		
		matcher = org.hamcrest.CoreMatchers.is(expextedStatus);
		resultMatcher = jsonPath("status", matcher);
		resultActions.andExpect(resultMatcher);
	}
	
	
}
