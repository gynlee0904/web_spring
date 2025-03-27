<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String subject = "공지사항 3월 이벤트가 진행되었습니다. 많은 관심 부탁드립니다.";
	String data = "냉장고,에어컨,김치냉장고,서랍"; 
	String mid = "홍길동";
	request.setAttribute("mid", mid);
	//jstl에서 jsp에서 생성된 변수를 받으려면 setAttribute가 있어야함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법 6 : 외부페이지 로드 </title>
</head>
<body>

<header>
	<nav>
	<c:set var="subject" value="<%=subject%>" />
	<c:set var="sub" value="${fn:length(subject)}" />
	<!-- length :()안의 길이 확인  -->
	<c:if test="${sub > 10}">
		<c:set var="dot" value="..." />
	</c:if>
	게시판 제목 : ${fn:substring(subject,0,10)}${dot}
    <!-- fn: 스페이스바 치면 쓸수있는 함수 다 나옴 -->
    
	<c:set var="a" value="연습" />
	<!-- c:set만 쓰면 외부페이지(jstl_top.jsp)에 변수 전달 못함 -->
	<c:import url="./jstl_top.jsp">
		<c:param name="z" value="${a}"></c:param>
	</c:import>
	<!-- import : 외부페이지 로드하는 태그 
		 url="" : 외부페이지 경로 및 파일명  -->
	<!-- param: 파라미터 name값으로 외부 페이지에 전달하는 방식 
		 외부페이지에서는 {param.name명}으로 전달받는다.
		 param 태그는 import 태그 안에서만 쓸 수 있음.  -->
	<!-- jstl_top.jsp을 바로 실행하면 변수값 전달 안됨 -->
	<!-- 컨트롤러에서 보낸 값은 do로 접속시 jstl_top.jsp에서 바로 받을 수 있음 -->
	</nav>
</header>
<!-- nav태그는 한번만 씀. header태그 안에 쓰거나 밖에 쓰거나. -->

<main>
<%-- <c:set var="product" value="${fn:split('<%=data%>',',')}" /> --%>

<c:set var="data" value="<%=data%>" />
<c:set var="product" value="${fn:split(data,',')}" />
<!-- fn은 직접적으로 jsp변수를 받을 수 없음. c:set의 변수로 한번 더 거쳐야함. -->
<!-- fn:함수명() 안에는 달러{}안씀 -->

<c:forEach var="pd" items="${product}">
${pd}
</c:forEach>
<c:import url="./jstl_main.jsp"></c:import>
</main>

<footer>
<c:import url="./jstl_bottom.jsp"></c:import>
</footer>
</body>
</html>