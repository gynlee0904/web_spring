//250227

function eventok(){
	if(frm.ename.value==""){
		alert("고객명을 입력하세요");
	}else if(frm.etel.value==""){
		alert("연락처를 입력하세요");
	}else if(frm.email.value==""){
		alert("이메일을 입력하세요");
	}else if(frm.ememo.value==""){
		alert("이벤트 참여이유를 입력하세요");
	}else if(frm.info1.checked == false){
		alert("개인정보활용에 동의하셔야만 이벤트 참여가 가능합니다");
	}else if(frm.info2.checked == false){
		alert("제3자 정보제공에 동의하셔야만 이벤트 참여가 가능합니다");
	}else {
		//정규식코드 (연락처확인)
		let ck = /^\d{2,3}\d{3,4}\d{4}$/;  //숫자외에 문자열 있을경우 false
		if(ck.test(frm.etel.value)==false){
			alert("전화번호를 정상적으로 입력하세요");
		} else{ //정규식코드가 true일 경우
			frm.submit();
		}
	}
};

//JS는 첫줄에 오류나면 그 다음은 인식을 못함 

//정규식코드 예시
function wordck(){
	var w = "010-1234-5678";
//	let ck = /[a-z]/;  //[a-z] : 소문자a~z까지 범위, / / : 정규식코드 기호 
//	console.log(ck.test(w));  //.test() 정규식코드 체크하는 함수
	// .test()=>true, false로 나옴 
	//[0-9] : 0~9까지 범위, [A-Z] : 대문자A~Z까지 범위
	//^[0-9] : 0~9숫자 외의 단어. ^:부정의 뜻 
	
//	let ck = /[0-9]/; 
//	console.log(w.match(ck));
	//.match()=> 배열형태의 값 출력 
	
//	let ck = /^[0-9]/g; 
	let ck = /^\d{2,3}-\d{3,4}-\d{4}$/; 
	//\d:숫자 {2,3}:2~3자리  {4}:4자리만 들어감
	//$ : 해당패턴에 문자열을 태입하여 체크하는 형식  
	console.log(ck.test(w));
	//w=010-1234-5678일때 true , w=01012345678일때 false
}