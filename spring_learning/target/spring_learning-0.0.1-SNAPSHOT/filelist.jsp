<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<String> filenm = (ArrayList)request.getAttribute("filenm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹디렉토리에 있는 첨부파일리스트</title>
</head>
<body>
<table border="1">
	<tr>
		<th>파일명</th>
		<th>삭제</th>
	</tr>
	<%
		int w = 0;
		while(w<filenm.size()){
	%>
	<%-- 반복문 안에는 name, id사용할때 신중해야함 id="abc"<%=w%> 이런식으로 쓰던가..다만 dto작성시 곤란해짐 --%>
	<tr>
		<td><%=filenm.get(w) %></td>
		<td><input type="button" value="삭제" onclick="file_del('<%=filenm.get(w)%>');"></td>
	</tr>
	<%
			w++;
		}
	%>

</table>
<!-- post방식으로 파일삭제하기 -->
<form id="fm" method="post" action="./filedel.do">
<!-- 선택한 파일의 파일명만 전송하니까 enctype=멀티파트어쩌고 필요없음  -->	
	<input type="hidden" name="fnm" value="">
</form>
</body>
<script>
function file_del(fnm){
	fm.fnm.value=fnm;
	fm.submit();
}

</script>
</html>