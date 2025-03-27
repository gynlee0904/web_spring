package spring_learning;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class macbook {
	/*Autowired, Inject : 의존성 주입(xml<=>자바 서로 주고받고 사용할수있게 하는것) 
	  xml과 연결되어 출력하는 역할 */
	
	@Resource(name="macbook_DAO")  //Resource : new와 동일한 작동함. Repository를 가져오는 역할
	private macbook_DAO dao; //DAO : sql쿼리문을 작동시키는 애
	//Repository와 Resource는 같이 세트로 움직임.
	
	@Resource(name="mac_dto") 
	private macbook_DTO macbook_DTO;
	
	/*Model과 HttpServletResponse는 함께 못씀 
	 
	*/
	
	
	//클래스리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(macbook_DTO dto, Model m) throws Exception{
//		List<macbook_DTO> : DTO형태의 배열로 생성하여 JSP로 전달 
		List<macbook_DTO> classList = this.dao.macbook_select();
//		System.out.println(classList.size());
//		System.out.println(classList.get(0).class_name);
		
		m.addAttribute("classList",classList);
		m.addAttribute("ea",classList.size());
		return null;
	}
	
	
	
	
	//클래스개설 메소드
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		try {
			int result = this.dao.macbook_insert(dto);
			String msg = "";
			if(result>0) {
				msg="alert('수업이 올바르게 생성되었습니다');"
						+"location.href='./macbook_list.do';";
			}
			m.addAttribute("msg",msg);
			
		} catch (Exception e) {
			
		}
		
		return "load";
	}
	
	
	//과정 수정페이지 출력 메소드
	@PostMapping("/mc_modify.do")
	public String macbook_modify(@RequestParam("midx") String mid, Model m) throws Exception {
//	public String macbook_modify(String midx, Model m) throws Exception {  //이렇게 써도 됨(@RequestParam("midx")생략) but 변수명은 FE의 name과 맞춰야함
		
		//DTO의 setter에 데이터 전달 
		macbook_DTO one_data = this.dao.macbook_one(mid);
//		System.out.println(one_data.class_name);  //DTO의 getter메소드를 호출 
		
		m.addAttribute("one_data",one_data);  //JSTL로 값 전달
		
		return "macbook_modify";
	}
	
	
	//과정 수정 메소드
	@PostMapping("/mc_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto,Model m) throws Exception {
		//insert, update, delete는 무조건 결과를 int로 받음
		int result = this.dao.macbook_modify(dto);  //DAO로 값 전송
		String msg = "";
		if(result>0) {
			msg="alert('수업이 올바르게 수정되었습니다');"
					+"location.href='./macbook_list.do';";
		}
		m.addAttribute("msg",msg);
		
		return "load";
	}
}
