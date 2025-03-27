package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import etc_model.m_md5_pass2;
import etc_model.m_md5_pass3;

@Controller
public class macbook_controller {
//추상클래스를 추가로 사용하고 싶으면 상속받은 추상클래스에 또 extends 하면 됨 

	@Resource(name="memberDTO") 
	private macbook_member_DTO memberDTO;
	
	@Resource(name="memberDAO") 
	private macbook_member_DAO memberDAO;
	
	
	@GetMapping("/macbook_user.do")
	public String macbook_user() {
		List<macbook_member_DTO> all = this.memberDAO.all_list();
		return null;
	}
	
	
	@PostMapping("/idsearch.do")
	public String idsearch(macbook_member_DTO dto, 
							@RequestParam(defaultValue ="N", required = false) String mcheck,
							Model m) {
		/*@RequestParam(defaultValue ="N", required = false) => 체크박스가 체크안된경우 N으로 처리해야함 
		 	@RequestParam : dto에 없는 name값을 처리할떄 주로 사용.
			defaultValue="" : name값의 value가 null이 날아왔을때 ""안의 값으로 작동 
			required = false : name value값이 필수가 아니라는 뜻(name값을 보내지 않아도 처리되도록). true가 기본(필수로 무조건 name값을 처리)  
			@RequestParam은 FE에서 값이 하나만 날아올 때 사용함.
		*/
		
		macbook_member_DTO userid = this.memberDAO.user_search(dto.mname, dto.memail);
		
		String msg ="";  //결과 관련사항을 출력
		
		if(userid==null) {
			msg = "아이디가 없습니다";
		}else {
			msg = userid.mid;
		}
		m.addAttribute("msg",msg);
		
		return "/WEB-INF/view/idsearch";
	}
	
	
	
	
	
	
	
	
	
	
	@Resource(name="md5_pass3")
	private m_md5_pass3 md;
	//다른 패키지에 Repository-Resource형태로 모델 있을 경우 webpage2.xml에 패키지 추가해줘야함

	//MD5로 데이터를 변환하는 형태의 컨트롤러
	@GetMapping("/macbook_login.do")
	public String macbook_login() {
		String pw="a123456";
		
		//md5로 사용자 패스워드를 변환하여 회신받음(Repository-Resource형태)
		String result = this.md.md5_make(pw);
		//dto를 직접 보낼수는 없음. String으로 변환해서 보내주는게 안전함
		
//		String result = this.md5_make(pw);  //abstract 형태(클래스에 extends 작성)
		System.out.println(result);
		
		return null;
	}
	
	
	
	
}
