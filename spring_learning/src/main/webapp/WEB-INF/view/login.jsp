<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 정보 출력</title>
</head>
<body>
<!-- .do를 통해 jsp를 출력해야 하는데 .jsp로 바로 접속(강제실행)하는것을 방지하기 위해 
WEB-INF폴더 안에 jsp를 모아두는 폴더를 만들어 jsp를 만들어 보관
왜?? 보안을 위해.
WEB-INF폴더는 Controller나 Model 외에는 접근을 못함.
컨트롤러가 작동을 해야 view(jsp)에 접근할 수 있음 
controller에서 return 사용시 "WEB-INF/디렉토리명/파일명" 형태로 작성.
서블렛에서도 RequestDispatcher 안에 동일한 경로 작성하면 동일하게 작동함 -->

아이디 : ${mid}
</body>
</html>