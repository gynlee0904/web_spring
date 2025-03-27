package spring_learning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//Spring Controller + View 기초 
//Controller어노테이션으로 컨트롤러로 만듦. @Controller를 꼭 붙여야 함!!! 없으면 모델로 인식. 
//하나의 컨트롤러에 여러개의 do를 만들수 있음 
//스프링에서 사용할수 있는 메소드 형태(4가지)
@Controller 
//@Controller : 일반클래스를 web에서 작동할 수 있도록 컨트롤러로 변경하게 해주는 어노테이션  
public class mainpage {
	PrintWriter pw = null;
			
	//@GetMapping : doGet메소드와 같음 
	//@PostMapping : doPost메소드와 같음 
	//@RequestMapping : doService와 같음(get,post 다 받음)
	@GetMapping("/abc.do")
	public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception{
	//req,res + throws어쩌고 가 있음 => view가 필요없는 메소드 
		
		res.setContentType("text/html;charset=utf-8");  
		//한글출력시킬때는 이걸 써줘야 한글 깨지지 않음 
		
		this.pw = res.getWriter();
		this.pw.write("<script>"+"alert('테스트요');"+"</script>");
		this.pw.close();
	}
	
	@PostMapping("/bbb.do")  //void메소드에서는 do랑 jsp 파일명을 일치시켜야함 
	public void bbb(HttpServletRequest req) {
	//req,res,throws가 없음 => view가 무조건 있어야 하는 메소드. 
	//webpage.xml에서 view를 찾음. view없으면 오류발생(404에러) 
		
		String pdnm = req.getParameter("pdnm");
		req.setAttribute("pdnm", pdnm);  //view(bbb.jsp로 이관)
		
		//RequestDispatcher가 없음. web.xml에서 처리해줌 
	}
	
	@PostMapping("/ccc.do")  //return메소드는 do와 jsp파일명을 다르게 할 수 있음
	public String ccc(HttpServletRequest req) {
		String pdnm = req.getParameter("pdnm");
		req.setAttribute("pdnm", pdnm);  
		
		return "/product_list";  
		//기본은 return null. null을 리턴하면 ccc.jsp를 찾음 (do랑 jsp 파일명을 일치시켜야함)
		//다른 jsp로 출력하려면 해당되는 경로를 써서 리턴해야함 (do명과 jsp파일명이 다를 수 있음)
	}
	
	
	@PostMapping("/ddd.do") 
	public ModelAndView ddd(HttpServletRequest req) {
		//ModelAndView : 스프링에서 사용할수 있는 자료형.(기본자료형: Object)배열과 같은 구조를 갖고있음 
		//모델에 있는 값도 가져와서 뷰로 전달해주는 메소드
		
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pdnm",pdnm);  //addObject : 키배열형태로 값을 저장. ("키",변수);
		mv.addObject("pcode",pcode);
		mv.addObject("pmoney",pmoney);
		//req로 veiw(jsp)에 전달하는 방식이 아님
		
//		return mv;  //ddd.jsp로 전달 
		
//		mv.setView(null); //ddd.jsp로 전달 
//		return mv;
		//setView() : ()안에 null혹은 비워둘경우 기본적으로 Mapping 이름과 동일한 jsp를 찾음.
		
		mv.setViewName("product_list"); //("")안의 경로로 전달 
		return mv;
		//setViewName("") : Mapping과 다른 이름의 파일로 전달하고 싶을 경우 사용. 
	}
	
	
	@PostMapping("/eee.do") 
	public String eee(HttpServletRequest req, Model m) {
	//Model : 스프링에서 사용할 수 있는 인터페이스.  
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		m.addAttribute("pdnm",pdnm);
		m.addAttribute("pcode",pcode);
		m.addAttribute("pmoney",pmoney);
		//addAttribute("키",변수) : jsp로 값을 전달 (jsp는 jstl형태로 출력)
		
		return "ddd";  //ddd.jsp로 전달 
	}
	
	
}
