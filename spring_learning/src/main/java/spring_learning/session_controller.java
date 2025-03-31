package spring_learning;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

//스프링전용 세션사용법 => 메소드()안에 HttpSession 인터페이스 사용 
@Controller
//@SessionAttributes("모델어트리뷰트에 대한 이름") //@SessionAttributes : 컨트롤러에만 사용하는 @ 
/* => 클래스에 해당@ 붙이면 해당 컨트롤러 안의 모든 메소드에 세션값을 Model로 전송가능 
 * DTO가 꼭 필요하며 Session형태의 DTO가 있어야 정상적으로 핸들링됨 
 * API서버 만들때 많이 쓰임. 일반 컨트롤러에 많이 쓰지는 않음.
 */
public class session_controller {
	@Autowired
	HttpSession hs;  //모든 메소드에서 HttpSession 사용하도록 의존성 주입 => 메소드()안에 일일히 HttpSession 안써도 됨 
	//특정 메소드에만 세션 사용하고 싶으면 해당 메소드 안에 HttpSession 쓰면 됨. 많은 메소드에서 쓰려면 필드에 올려두는것 권장
	//@Autowired : 스프링에서 사용하는 의존성주입 @ 
	//의존성 주입 형태로 인터페이스를 필드에 선언하여 모든 메소드에 세션을 적용할 수 있음 
	
	@GetMapping("/session1.do")
	public String session1(HttpSession se) {
		//HttpSession 인터페이스 => HttpServletRequest req 
		//컨트롤러는 물론 DAO, DTO, VO, 모델에도 사용 가능 
		String userid="kim";
		se.setAttribute("mid", userid);  //세션에 저장 
		
		return null;
	}
	
	@GetMapping("/session2.do")
	public String session2(HttpSession se) {
		String id = (String)se.getAttribute("mid");  //"mid" 키값의 세션 가져옴 
		System.out.println(id);
		return null;
	}
	
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name="mid", required = false) String mid) {
		System.out.println(mid);   //=> kim 출력
		//getAttribute("mid") 를 안써도 @SessionAttribute 사용해서 세션 가져옴
		//주의사항 : 세션값이 null일 경우 name="mid" 이 없으면 400에러뜰 수 있음 
		//여러 세션값 가져오고 싶으면 @Requestparam처럼 각각 써주면 됨
		
		String test = (String)this.hs.getAttribute("mid");  //필드에 있는 세션을 사용 
		System.out.println("test : " + test);    //=> kim 출력
		//mid라는 키로 세션에 저장되어있기 때문에 필드에 있는 세션에도 값이 저장되어있음 
		
		return null;
	}
	
	//세션 파기 
	@GetMapping("/session4.do")
	public String session4() {
		//필드에 올려둔 세션을 로드하여 세션 초기화 
		this.hs.invalidate();  //모든 세션 초기화 
		
//		this.hs.removeAttribute("mid");   //특정 키의 세션만 삭제 
		return null;
	}
	
	
	//해당 세션 생성 후 문자열 변수로 변환하여 모델로 전달 => jsp에 출력 
	@GetMapping("/session5.do")
	public String session5(Model m) {
		String userid="lee";
		this.hs.setAttribute("mid", userid);  //세션 만들어짐
		
		String id = (String)this.hs.getAttribute("mid");  // 세션 가져옴 
		System.out.println(id);
		
		m.addAttribute("mid", id);
		return "session";
	}
	
	
	
	
}
