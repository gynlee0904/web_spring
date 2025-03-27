<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 </title>
</head>
<body>
<form id="frm" method="post" action="mc_modifyok.do">
<input type="hidden" name="midx" value="${one_data.midx}">
<!-- 수정, 삭제시에는 무조건 고유값 태워보내야함 -->

과정부분 : <!-- FE JS로 선택값 출력하기 -->
<select name="class_part">
	<option value="온라인 상시과정">온라인 상시과정</option> 
	<option value="정규과정" >정규과정</option> 
	<option value="집합과정" >집합과정</option> 
</select>

<br>
과정 카테고리 : <!-- BE JSTL로 선택값 출력하기 -->
<select name="class_cate">
	<option value="교육" <c:if test="${one_data.class_cate=='교육'}">selected</c:if> >교육</option> 
	<option value="보강" <c:if test="${one_data.class_cate=='보강'}">selected</c:if> >보강</option> 
	<option value="자격증" <c:if test="${one_data.class_cate=='자격증'}">selected</c:if> >자격증</option> 
</select>
<br>
과정명 : <input type="text" name="class_name" value="${one_data.class_name}"> 
<br>
학습일수 : <input type="text" name="class_day" value="${one_data.class_day}"> 
<br>
정가 : <input type="text" name="class_price" value="${one_data.class_price}"> 
<br>
수강료 : <input type="text" name="class_sales" value="${one_data.class_sales}"> 
<br>
과정소개 : <textarea cols="100" rows="5" name="class_info">${one_data.class_info}</textarea> 
<br>
강사명 : <input type="text" name="class_teacher" value="${one_data.class_teacher}"> 
<br>
학습목표 : <textarea cols="100" rows="5" name="class_object" >${one_data.class_object}</textarea> 
<br>

강의진행여부 : 
<input type="radio" value="Y" name="class_use" <c:if test="${one_data.class_use=='Y'}">checked</c:if>>진행 
<input type="radio" value="N" name="class_use" <c:if test="${one_data.class_use=='N'}">checked</c:if>>정지
<br>
<button type="button" onclick="save_class();">강의 수정</button>
</form>
</body>

<script>
var subject = "${one_data.class_part}";
</script>
<!--  JS변수(하위)는 BE(상위)의 모든 변수값을 받을 수 있음 -->
<!--  ★★★★★.js파일에서는 BE, JSTL코드 못씀. jsp페이지 안에서 달러{}를 써야함(전역변수로 사용)★★★★★ -->

<script src="./macbook.js?v=4"></script>
</html>