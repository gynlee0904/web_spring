<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[]data = new String[]{"hong","kim","park"};
	request.setAttribute("data", data);  //=>jstl에서 ${data}로 쓸수있음
			
	ArrayList<String> data2 = new ArrayList();
	data2.add("hong1");
	data2.add("kim1");
	data2.add("park1");
	data2.add("seo1");
	data2.add("kang1");
	request.setAttribute("data2", data2);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법 3 : 반복문 foreach(core에만 있음) </title>
</head>
<body>
<table border="1">
	<tr>
	<c:forEach var="z" begin="1" end="5">
		<td>${z}</td>
	</c:forEach>	
	<!-- forEach : jstl에서 유일하게 배열 및 반복문으로 사용하는 속성 
		 begin="" : 시작값
		 end="" : 종료값 -->
	</tr>
	
	<tr>
	<c:forEach var="zz" items="<%=data %>">
	<%-- items="" : 배열값을 받는 속성 
		 ${data}로 쓰려면 setAttribute의 값을 가져온거고 
		 setAttribute 없으면 <%=data%>로 써도 됨 --%>
		<td>${zz}</td>
	</c:forEach>	
	</tr>
	
	<tr>
	<c:forEach var="zzz" items="${data2}" begin="1" end="3">
	<!-- begin="1" end="2" => 배열인덱스1번부터 3번까지 출력 -->
		<td>${zzz}</td>
	</c:forEach>
	</tr>
	
	<tr>
	<c:forEach var="a" items="${data2}" begin="0" end="4">
		<c:if test="${a != 'kim1'}">
			<td>${a}</td>
		</c:if>
	</c:forEach>
	</tr>
	
	
</table>
</body>
</html>