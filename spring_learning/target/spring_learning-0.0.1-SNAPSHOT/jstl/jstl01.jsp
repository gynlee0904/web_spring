<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 사용시 필요한 엔진. 필요한것만 가져와 쓰면됨 (core는 꼭 써야함)  -->
<%@taglib prefix="n" uri="http://java.sun.com/jsp/jstl/core"%> 
<!-- prefix=""은 태그 쓸때 선언하는 닉넴같은거임. 암거나 쓰면 됨(꼭 c가 아니어도 되지롱) -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl 각종 함수 -->
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  <!-- jstl db관련사항 -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  <!-- jstl 포맷옵션 (금액,날짜정보,시간,통화기호 같은거) -->

<%
	String name = "홍길동";  //JSP변수선언 
	HttpSession hs = request.getSession();  //JSP로 세션 생성하는 방식
	hs.setAttribute("ssdata", "09041011");
	String se = (String)hs.getAttribute("ssdata");  //jsp세션 출력
// 	out.print(se);
	
	String tels = (String)hs.getAttribute("tel");  //jstl로 만든 세션값 출력
// 	out.print(tels);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법 1 : set, session </title>
</head>
<body>
<!-- jstl 태그 형태 -->
<n:set var="a" value="강감찬" /> 
<!-- set : core태그 속성중 하나. setAttribute와 같음 
	 var="변수명" value="값"-->
<input type="text" name="mname" value="${a}">

<br>
<!-- JSP 변수 받는 방법  -->
<n:set var="nm" value="<%=name %>" />
고객명 : ${nm}

<br>
<% String money ="50000";  //jsp로 변수선언 %>
<n:set var="kk" scope="request"><%=money%></n:set>
${kk}
<!-- scope="request"는 기본값이라 생략할수 있음(어차피 잘 안씀ㅋ) -->
<br>
<n:out value="값출력" />
<!-- out : core태그 속성중 하나. out.print와 같음. 근데 잘 안씀  
	 value="출력할 내용"-->
	 
<br>
<!-- jstl로 세션을 생성하는 방식 -->
<n:set var="tel" value="jstl로 만든 세션이지롱" scope="session" />
<!-- scope="" : session, request, page(=>다른jsp에 있는 지역변수 가져올 수 있음).. 
	 scope="session" => 세션 만들기 
	 var=""에 기존 생성된 세션의 키값과 동일한 이름은 넣으면 안됨(초기화됨) -->
세션 데이터(jsp로 만든것): ${ssdata} <br>
세션 데이터(jstl로 만든것): ${tel}
<!-- 세션만들때는 jsp로 만들던가 jstl로 만들던가 둘중 하나로만 하는게 좋음 -->

</body>
</html>