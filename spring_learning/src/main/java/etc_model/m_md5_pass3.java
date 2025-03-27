package etc_model;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

@Repository("md5_pass3")
public class m_md5_pass3 {
	
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

