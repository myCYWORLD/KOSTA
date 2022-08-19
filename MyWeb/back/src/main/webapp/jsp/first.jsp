<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first jsp</title>
</head>
<body>
<h1>FIRST JSP</h1> jsp용 java 파일이 자동 만들어짐.
<%-- <%int i; %> <!--지역변수 선언--> --%>
<%-- <%out.print(i); %>  --%><!--컴파일 오류 : 초기화 안되어있는 지역변수라 사용할수 x-->
<%int i = 99; %> 
<%out.print(i); %> <!--99출력-->
<hr>
<%= i%>    <!--99출력-->
<hr>
<%! int i; %> 
<%=i %>  <!--99출력-->
<hr>
<%=this.i%> <!-0출력-->
</body>
</html>