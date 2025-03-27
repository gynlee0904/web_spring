package spring_learning;

import lombok.Getter;
import lombok.Setter;

//lombok사용법 : pom.xml에 라이브러리 등록(=>@Setter,@Getter 사용가능해짐) + lombok.jar 설치  
@Setter
@Getter
public class user_DTO {
	String mid, mpass;  //FE에서 넘어오는 name값과 일치해야함
	String mname, memail; //FE에서 넘어오는값이 없으면 null로 넘어감
	//DTO 하나로 회원가입, 로그인, 아이디 찾기 등 여러 용도로 쓰일 수 있음 
}
