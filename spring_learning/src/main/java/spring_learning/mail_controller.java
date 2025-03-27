package spring_learning;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mail_controller {
	
	
	@PostMapping("/contactusok.do")
	public String contactusok(@RequestParam String subject,
								@RequestParam String mname, 
								@RequestParam String memail,  
								@RequestParam String mtext) throws Exception {
		
		Properties props = new Properties();  //배열형태의 구성 가지고 있음. Map이 발전된 형태 (.ini =>환경설정 파일 형태의 배열)
		props.put("mail.smtp.host","smtp.naver.com");  //메일 발송서버 
		props.put("mail.smtp.port", "587");  //메일 발송 서버의 포트 
		props.put("mail.smtp.auth", "true");  //★메일 발송 서버의 인증(아이디,패스워드). false가 되면 인증 안하겠다는뜻 
		props.put("mail.smtp.starttls.enable", "true");  //★메일 서버의 TLS를 연결 
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");  //메일 서버의 ssl기능(보안)사용시 ssl기준으로 메일발송하겠다는 뜻
		props.put("mail.smtp.ssl.protocols","TLSv1.2");
		
		//메일 발송에 대한 로그인(메일 서버 로그인 정보)사항을 세션으로 등록 (=>서버에 대한 자동로그인 기능) 
		Session session = Session.getInstance(props, new Authenticator() {
			
			//로그인할 아이디와 패스워드 정보를 입력시킴 
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gynlee01@naver.com","메일비번");
			}
		});
		
		//메일 발송 오류발생시 왜 오류가 났는지 확인을 위한 예외처리 
		try {  //메일 내용 발송하는 부분 
			Message msg = new MimeMessage(session);
			//Mime : 이메일 전송을 위한 인터넷 표준 포멧 
			
			msg.setFrom(new InternetAddress(memail,mname,"utf-8"));  //폼 작성시 메일보내는 사람 세팅(보내는사람도 smtp 허용되어있어야함)
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("gynlee01@naver.com"));  //메일 받는 사람 메일주소, 이름, 언어셋
			//=>이게 정상이라는데??
			
//			msg.setFrom(new InternetAddress("gynlee01@naver.com","이기윤","utf-8"));  //폼 작성시 메일보내는 사람 세팅
//			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(memail,mname,"utf-8"));  //메일 받는 사람 메일주소, 이름, 언어셋
			//=>얘는 발송됨
			
			msg.setSubject(subject);
			msg.setContent(mtext,"text/html;charset=utf-8");
			
			Transport.send(msg);  //메일발송 
			
		} catch (Exception e) {  //메일 발송에 대한 서버 접근오류 발생시 출력
			System.out.println("e : "+e);
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
