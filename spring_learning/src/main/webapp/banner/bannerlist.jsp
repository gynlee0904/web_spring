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
		<th><input type="checkbox" id="all_ck" onclick="check_all(this.checked);"></th>
		<!-- (this.checked) : 체크 여부를 확인 -->
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
<!-- !!!반복문 안에는 id="", form 절대 사용안함!!! name이나 class로 핸들링함 -->
	<tr height="50">
		<td><input type="checkbox" name="ckbox" value="${bn.bidx}" ></td>
		<!-- bn.bidx : db에서 사용하는 자동증가값 -->
		<!-- data-value="", aaavlaue=""같은 식으로 쓰고 JS에서 핸들링하는 경우도 있음 -->
		
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
<form id="dform" method="post" action="./bannerdel">
<input type="hidden" name="ckdel" value="">
</form>
<input type="button" value="선택삭제" onclick="checkdel();">
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
//페이징
function pg(no){
	location.href="./bannerlist?pageno="+no;
}
	
//검색
function spage(){
	if(sform.search.value==""){
		alert("검색어를 입력하세요");
		return false;
	}else{
		return;
	}
}

//전체선택 관련 핸들링 함수 
function check_all(ck){  //ck => 체크되면 true 아니면 false 
	var ea = document.getElementsByName("ckbox");
// 	console.log(ea.length)  //5개 나옴

	//if문 없이 이벤트 핸들링
	var w=0;
	while(w<ea.length){
		ea[w].checked = ck;
		w++;
	}
	
	/* ↓길게 쓴 경우
	if(ck==true){  //전체선택 체크를 했을 때
		var w=0;
		while(w<ea.length){
			ea[w].checked = true;
// 			ea[w].checked = "checked";
			w++;
		}
	}else {  //전체선택 체크 해제했을 때
		var w=0;
		while(w<ea.length){
			ea[w].checked = false;
			w++;
		}
	}
	*/
}

//쳌박 선택된 배너 삭제 함수 
function checkdel(){
	var arr = new Array();  //JS에서 사용하는 배열(스크립트 배열, VUE등에서도 사용.)
	
	var ob = document.getElementsByName("ckbox");
	var w= 0;
	while(w<ob.length){
		if(ob[w].checked == true){
// 			console.log(ob[w].value);
			arr.push(ob[w].value);  //배열에 선택된 체크박스의 bidx 담음 
		}
		w++;
	}
	dform.ckdel.value=arr;  //hidden input의 value값에 배열값 넣음 
	//=> html은 배열 못담음. 자동으로 문자화시켜 담게됨. value="[1,2,3]" (x) => value="1,2,3" (o)
	
	if(confirm("해당데이터 삭제시 복구 불가")){
		dform.submit();
	}
}
</script>
</html>