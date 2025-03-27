<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법 2 : if조건문(core에만 있음) </title>
</head>
<body>
<c:if test="${5<10}" var="pp"></c:if>
${pp}
<!-- test="" : if()와 같음. 조건사항. 
	 조건사항이 맞으면 true, 틀리면 false로 출력 
	 변수를 태그 안에 넣으면 false일때 아무것도 안뜸 -->

<br>
<c:set var="a" value="30" />
<c:set var="b" value="50" />
<c:if test="${a>b}">a값이 큽니다</c:if> <!-- false니까 암것도 안뜸 -->
<c:if test="${a<b}">b값이 큽니다</c:if> <!-- "b값이 큽니다" 출력 -->
<!-- jstl if문은 else if나 else가 없음. 걍 if만 계속 쓰면 됨 -->

<br>
<c:set var="a" value="30" />  <!-- => 30은 문자로 인식됨 -->
<fmt:formatNumber value="${a}" type="number" var="aa" />  <!--  =>숫자로 변환 -->
<!-- jstl 비교연산자 
	 lt(<) , gt(>) , le(<=) , ge(>=) 
	 숫자변수 형태로 if문 사용시 에러 발생할 수 있음 -->


<br>
<c:set var="product" value="그래픽카드" />
<c:if test="${product=='그래픽카드'}"> 가격미정 </c:if>  <!-- true니까 "가격미정"출력됨 -->
<c:if test="${product=='그래픽카드!!'}"> 가격미정2 </c:if>  <!-- false니까 암것도 안뜸 -->

<br>
<c:set var="product2" value="모니터" />
<c:if test="${product eq '그래픽카드' || product2 eq '모니터'}"> 가격미정3 </c:if>  <!-- true -->
<c:if test="${product eq '그래픽카드' && product2 ne '모니터2'}"> 가격미정4 </c:if> <!-- true -->
<!-- eq는 ==과 같음. ne는 !=과 같음 
	 or는 ||과 같음. and는 &&와 같음 -->

<br>
<!-- choose (=if문세팅) , when (=if~else if), otherwise (=else) -->
<c:set var="agree" value="Y" />
<c:choose>
	<c:when test="${agree == 'Y'}">약관에 동의했음</c:when>
	<c:when test="${agree == 'N'}">약관에 동의안함</c:when>
	<c:otherwise>약관확인불가</c:otherwise>
</c:choose>









</body>
</html>