<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법 5 : database연결 </title>
</head>
<body>

<sql:setDataSource var="db" 
	driver="com.mysql.cj.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/mrp"
	user="project"
	password="a123456" />
	
<!-- DDL 1방식 -->
<%-- <sql:query var="ps" sql="select * from event" dataSource="${db}" /> --%>
<%-- <c:forEach var="pp" items="${ps.rows}">${pp}</c:forEach> --%>
<!-- .rows : database 테이블의 전체값  -->

<!-- DDL 2방식 -->
<br>
<sql:query var="ps" dataSource="${db}" > select * from event </sql:query>
<c:forEach var="pp" items="${ps.rows}">
고객명 : ${pp['ename']} , 이메일 : ${pp['email']} 이벤트 등록일 : ${fn:substring(pp['ejoin'],0,10)}<br> 
<%-- fn은 ${}안에서 씀(태그가 없음) --%>
</c:forEach>

<br>
<c:set var="tables" value="event" />
<sql:query var="pss" dataSource="${db}" > select * from ${tables} </sql:query>
<c:forEach var="ppp" items="${pss.rows}">
고객명 : ${ppp['ename']} , 이메일 : ${ppp['email']} 이벤트 등록일 : ${fn:substring(ppp['ejoin'],0,10)}<br> 
</c:forEach>

</body>
</html>