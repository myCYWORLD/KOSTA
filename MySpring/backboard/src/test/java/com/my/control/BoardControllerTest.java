package com.my.control;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)  //스프링컨테이너 구동 : ApplicationContext타입의 컨테이너
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration //WebApplicationContext타입의 컨테이너
public class BoardControllerTest {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc; //컨트롤러 테스트용 모의객체

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testList() throws Exception {
		//게시글페이지별 목록 테스트
		String uri = "/boardlist"; //http://localhost:8888/bckboard/boardlist
		String currentPage = "2"; //글번호 목록 2 4 6
		MockHttpServletRequestBuilder  mockRequestBuilder =
				MockMvcRequestBuilders.get(uri).accept(org.springframework.http.MediaType.APPLICATION_JSON);
		mockRequestBuilder.param("currentPage", currentPage);
		//응답정보: ResultAction
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);//요청
		resultActions.andExpect(MockMvcResultMatchers.status().isOk()); //응답상태코드200번 성공 예상
//		resultActions.andExpect(jsonPath("status",is(1)));//json객체의 status프로퍼티값이 1 예상
		
		org.hamcrest.Matcher<Integer> matcher;
		JsonPathResultMatchers jsonPathMatcher; 
		ResultMatcher resultMatcher;
		jsonPathMatcher = jsonPath("status");  //jsonPath 응답된 제이슨 문자열을 쉽게 찾아갈 수 있는 문법?
		resultMatcher = jsonPathMatcher.exists();
		resultActions.andExpect(resultMatcher);
		
		int expectedStatus = 1;
		matcher = org.hamcrest.CoreMatchers.is(expectedStatus);		
		resultMatcher = jsonPath("status", matcher);
		resultActions.andExpect(resultMatcher);

		//json객체의 "t"프로퍼티의 자료형은PageBean이고 PageBean의 "list"프로퍼티 자료형은 List이다
		jsonPathMatcher = jsonPath("t.list");
		resultMatcher = jsonPathMatcher.exists();
		resultActions.andExpect(resultMatcher);

		//게시글목록의 크기 t.list.length()
		int expectedListSize = 3;
		matcher = org.hamcrest.CoreMatchers.is(expectedListSize);		
		resultMatcher = jsonPath("t.list.length()", matcher);		
		resultActions.andExpect(resultMatcher);

		//게시글목록의 첫번째요소 t.list[0].boardNo
		int expectedBoardNo = 4;
		matcher = org.hamcrest.CoreMatchers.is(expectedBoardNo);		
		resultMatcher = jsonPath("t.list[0].boardNo", matcher);
		resultActions.andExpect(resultMatcher);
		
		//게시글페이지그룹의 시작페이지값 t.startPage
		int expectedStartPage = 1;
		matcher = org.hamcrest.CoreMatchers.is(expectedStartPage);
		resultMatcher = jsonPath("t.startPage", matcher);
		resultActions.andExpect(resultMatcher);
	}
}
