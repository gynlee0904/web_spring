<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!-- jstl06과 연계 -->
<style>
.menu{
	width : 500px;
	height: 30px;
	display : flex;
	flex-direction:row;
	justify-content: center;
	align-content: center;
}

.menu > span {
	display:block;
	border : 1px solid black;
	width : 200px;
	height: inherit;
	text-align: center;
	line-height: 30px;
}
/* form태그 쓸때 .menu와 <span>사이에 form이 들어가면 css 깨짐. 밖에 쓸것 */
</style>

<title>상단 페이지</title>
아이디 : ${mid} , 레벨 : ${level}
<!-- jstl_top.jsp을 바로 실행하면 변수값 전달 안됨 -->
<!-- 컨트롤러에서 보낸 값은 do로 접속시 바로 받을 수 있음 -->
<div class="menu">
	<span>수강신청</span>
	<span>학습지원센터</span>
	<span>나의강의실 ${param.z}</span>
</div>