<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms 상담신청 내역 확인 페이지</title>
</head>
<body>
제목 : ${subject} <br>
상담자명 : ${cuser} <br> 
상담목적 : 
<input type="checkbox" value="cms1" <c:if test="${fn:contains(cate,'cms1')}">checked</c:if> disabled>심리상담 
<!-- disabled : 해당내용을 수정 및 변경 못하게하는 속성. (input, checkbox, radio에서 사용)
	 단, BE로 값을 전송시 disable 처리시 name속성이 있어도 BE로 값 못 넘김 -->
<input type="checkbox" value="cms2" <c:if test="${fn:contains(cate,'cms2')}">checked</c:if> disabled>학교생활 
<input type="checkbox" value="cms3" <c:if test="${fn:contains(cate,'cms3')}">checked</c:if> disabled>가정문제 
<input type="checkbox" value="cms4" <c:if test="${fn:contains(cate,'cms4')}">checked</c:if> disabled>학업 및 진로
<input type="checkbox" value="cms5" <c:if test="${fn:contains(cate,'cms5')}">checked</c:if> disabled>대인관계
<!-- fn:contains() : 해당 단어가 포함되어있는지 확인하는 함수 -->  
${cate}
</body>
</html>