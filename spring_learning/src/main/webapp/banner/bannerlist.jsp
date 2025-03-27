<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너리스트</title>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0">
<thead>
	<tr>
		<th><input type="checkbox" ></th>
		<th width="80">번호</th>
		<th width="300">배너명</th>
		<th width="100">이미지</th>
		<th width="150">파일명</th>
		<th width="100">등록일</th>
	</tr>
</thead>
<tbody>
<c:forEach var="bn" items="${all}">
	<tr height="50">
		<td><input type="checkbox" ></td>
		<td></td>
		<td>${bn.bname}</td>
		<td>
			<c:if test="${bn.file_url==null}">
				No Image
			</c:if>
			<c:if test="${bn.file_url!=null}">
				<img src="..${bn.file_url}" width="100" height="50">
			</c:if>
		</td>
		<td><a href="../file/${bn.file_renm}" target="_blank" title="${bn.file_orinm}">${bn.file_orinm}</a></td>
<%-- 	<a href="..${bn.file_url}" target="_blank"> --%>
		<td align="center">${fn:substring(bn.bdate,0,10)}</td>
	</tr>
</c:forEach>
</tbody>



</table>
</body>
</html>