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
<form id="sform" method="get" action="./bannerlist" onsubmit="return spage()">
<p> 배너명 검색 : <input type="text" name="search" value="${search}">
<input type="submit" value="검색" >
<input type="button" value="전체목록" onclick="location.href='./bannerlist';">
</p>
</form>
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
<c:if test="${fn:length(all)==0}">  <!-- all(=>배열)의 데이터 개수가 0일때. -->
<!-- 배열값을 조건문으로 jstl에 처리시 function 이용하여 length로 검토하여 처리함 -->
<tbody>
<tr height="50">
<td colspan="6" align="center">검색된 데이터가 없습니다</td>
</tr>
</tbody>
</c:if>
<tbody>
<c:set var="ino" value="${total-userPage}" />  <!-- 게시물 일련번호 세팅 -->
<c:forEach var="bn" items="${all}" varStatus="idx">
	<tr height="50">
		<td><input type="checkbox" ></td>
		<td align="center">${ino-idx.index}</td>
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
<br><br>
<!-- 폼전송으로 선택된 값을 삭제하는 프로세서 -->
<form>
<input type="hidden">
</form>
<input type="button" value="">
<br><br>
<!-- 페이징 -->
<table border="1" cellpadding="0" cellspacing="0">
<tbody>
<tr height="30">
<!-- 컨트롤러에서 데이터의 전체 개수(total)를 받음. 
	 해당값을 한페이지당 5개씩 출력하는 구조
	 ${total / 5 + (1-(total/5)%1)%1} => 총 페이징 번호를 생성 -->
<c:set var="pageidx" value="${total / 5 + (1-(total/5)%1)%1}" />
<c:forEach var="no" begin="1" end="${pageidx}" step="1">  <!-- step="" : 1씩 증가. 안써도 됨 -->
<td width="30" align="center" onclick="pg('${no}')">${no}</td>
</c:forEach>

</tr>
</tbody>
</table>

</body>
<script>
function pg(no){
	location.href="./bannerlist?pageno="+no;
}
	

function spage(){
	if(sform.search.value==""){
		alert("검색어를 입력하세요");
		return false;
	}else{
		return;
	}
}

</script>
</html>