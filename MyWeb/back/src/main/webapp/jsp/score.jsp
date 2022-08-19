<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score.jsp</title>
</head>
<body>
<%! int totalScore = 0; //객체의 멤버변수 
/* 객체 생성시 한번만 만들어놓고 요청과 관계없이 계속 사용할 수 있으려면 declaration */
	int totalCount = 0;
%>
<%--요청전달 데이터 얻기 --%>
<%
String score = request.getParameter("score"); 
totalScore += Integer.parseInt(score);
totalCount++;
%>
<%=score%>점을 선택하셨습니다
<hr>
총점은 <%=totalScore%>점 입니다.<br>
참여인원은 <%=totalCount %>명 입니다.<br>
평점은 <%= (float)totalScore/totalCount%>점 입니다.<br>
<a href="/front/html/score.html">별점주기</a>
</body>
</html>