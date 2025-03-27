package etc_model;

import java.security.MessageDigest;


public abstract class m_md5_pass2 { 
	
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

//패키지가 다른 경우 컨트롤러에서 extends할 수 없음
//public abstract으로 바꾸고 컨트롤러에서 extends 해야함.
//public없는 abstract는 다른패키지에서 못불러옴 

