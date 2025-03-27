<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	ModelAndView mv = (ModelAndView)request.getAttribute("pdnm");
	//getAttribute는 컨트롤러에서 setAttribute로 던져줘야 쓸 수 있음 	
%>
<!-- servlet의 기본은 jsp 
	 Spring의 기본은 jstl (엔진필요)
	 Spring-boot의 기본은 thymeleaf (엔진필요) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ddd view</title>
</head>
<body>
<!-- 컨트롤러에서 ModelAndView 메소드를 사용시 jstl로 출력해야함.
	 달러{변수명(=키값)} 형태로 데이터 전달받음 -->
상품명 : ${pdnm} <br>
상품코드 : ${pcode}<br>
상품가격 : ${pmoney}
</body>
</html>