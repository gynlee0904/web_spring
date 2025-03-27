<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String member[][] ={
						{"hong","kang"},
						{"A형","B형"},
						{"19살","29살"}
					};

	ArrayList<ArrayList<String>>  all = new ArrayList<ArrayList<String>>();
	ArrayList<String> data = new ArrayList<String>();
	data.add("에어프라이어");
	data.add("냉장고");
	data.add("에어컨");
	all.add(data);
	
	
	ArrayList<String> data2 = new ArrayList<String>();
	data2.add("250000");
	data2.add("350000");
	data2.add("450000");
	all.add(data2);
	
// 	out.print(all);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법 4 : 2차반복문 </title>
</head>
<body>
<c:set var="aa" value="<%=member[1]%>" /> 
<c:set var="bb" value="<%=member[2]%>" /> 
<c:forEach var="cc" items="<%=member[0]%>" varStatus="no">
고객명 : ${cc} , 혈액형 : ${aa[no.index]} , 나이 : ${bb[no.index]}<br>
<!-- varStatus="" : 배열번호가져오는 변수(0부터 시작) 
	 varStatus변수.index : 노드번호 가져옴 -->
</c:forEach>

<br>
<!-- 클래스배열에 jstl 출력 -->
<c:set var="dd" value="<%=all.get(1)%>" /> <!-- 가격데이터 전체를 가져옴 -->
<c:forEach var="ee" items="<%=all.get(0)%>" varStatus="no">
번호 : ${no.index+1}, 제품명 :${ee} , 가격 : ${dd.get(no.index)} <br>
</c:forEach> 







</body>
</html>