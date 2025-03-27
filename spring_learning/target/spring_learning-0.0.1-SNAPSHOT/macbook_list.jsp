<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 리스트</title>
</head>
<body>
<p>개설된 과목갯수 : ${ea}</p>
<form id="frm" method="post" >
<input type="hidden" name="midx" value="">
<!-- DTO에 있는 변수명으로 JSTL로 출력  -->
<c:forEach var="cdata" items="${classList}" varStatus="idx">
	과정명 : ${cdata['class_name']} , 
	강사명 : ${cdata.class_teacher} , 
	수강료 : ${cdata.class_sales} 
	<input type="button" value="수정" onclick="mc_modify('${cdata.midx}');">
	<input type="button" value="삭제" onclick="mc_del('${cdata.midx}');"> <br>
</c:forEach>
</form>
<div id="msg"></div>
</body>
<script>

function mc_modify(n) {
	//GET으로 보내기 1
// 	location.href = './mc_modify.do?midx='+n;

	//GET으로 보내기 1
// 	frm.midx.value=n
	//input hidden으로 여러값을 보냄 

	//post로 보내기 1
// 	var f = document.getElementById("msg");
// 	f.innerHTML = `<form id="frm" method="post" action="./mc_modify.do">
// 		<input type="hidden" name="midx" value="`+n+`">
// 		</form>`
		
	//post로 보내기 2
	frm.midx.value=n
	frm.action="./mc_modify.do";
	frm.submit();
	
}

function mc_del(n) {
	if(confirm("진짜 삭제할거임? \n 삭제후 복구불가")){
		frm.midx.value=n
		frm.action="./mc_delete.do";  //form을 2개 만들기보다 js로 action값을 핸들링 
		frm.submit();
	}
}
</script>
</html>