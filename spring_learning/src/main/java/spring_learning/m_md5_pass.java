package spring_learning;

import java.security.MessageDigest;

//@Repository("md5_pass")
//public class m_md5_pass {
abstract class m_md5_pass {  //추상클래스로 만들고 컨트롤러에서 상속받아 사용
	//추상클래스를 추가로 사용하고 싶으면 상속받은 추상클래스에 또 extends 하면 됨 
	
	//사용자 비밀번호 및 게시판 글 등록시 사용, 비회원에 사용하는 암호화
	public String md5_make(String pw) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(pw.getBytes());
			for(byte b : md.digest()) {
				sb.append(String.format("%x",b));
			}
		} catch (Exception e) {

		}
		
		return sb.toString();
	}
}

