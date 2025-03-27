package spring_learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
//webpage2.xml에서 컨트롤러 빈 등록을 해야 컨트롤러로서 작동함
public class mainpage02 {

	/*DTO로 FE의 값을 받을 수 있음.(lombok라이브러리가 있어서)
	  DTO를 수정하지 않고 별도로 값을 받아 해결해야할 경우 서블릿 형태의 request로 받으면 됨 
	  ★★★FE의 네임값과 동일하게 DTO가 작성되어야 함★★★
	  
	  DTO활용법 : FE로 값 이관, Model에 값 이관, Database에서도 사용
	*/
	@GetMapping("/login.do")
	public String aaa(user_DTO dto, HttpServletRequest req, Model m) {
	//메소드명은 다른 클래스에 있는 메소드명과 같아도 되지만 do명은 겹치면 안됨 	
		String ck = req.getParameter("mck");  //dto에 없는 변수 받을수도 있음
		
		System.out.println(dto.getMid());
		System.out.println(dto.getMpass());
		System.out.println(ck);
		System.out.println(dto.getMname());
		System.out.println(dto.getMemail());
		
		//Model인터페이스로 해당되는 jsp에 변수를 넘겨줌 
		m.addAttribute("mid", dto.getMid());
		
		return "WEB-INF/view/login";
		/*return "WEB-INF/view/login"; => return "login"로 쓰려면 
		  webpage2.xml에서 <beans:property name="prefix" value="/WEB-INF/view/">로 써두면 됨
		 */
	}
	
	//Database + XML + Connection + Controller
	@Autowired  //@Autowired : 의존성 주입. 자바에서 사용하는 클래스 또는 인터페이스의 값을 xml에 있는 id값으로 대체하게 하는 어노테이션. 
	BasicDataSource dbinfo;  //=>db_config.xml의 <bean>의 id값
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest req) {
		try {
			//db_config.xml에있는 정보를 Connection으로 전달 
			this.con = this.dbinfo.getConnection();
//			System.out.println(this.con);  //db연결 확인 
			
			String sql = "select * from event order by eidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			req.setAttribute("rs", this.rs);  
			//ResultSet을 jsp로 전송 => this.ps, this.rs를 close할수없음(close하면 뷰가 작동 못함)
			//배열이나 dto를 써서 넘겨야 함
			
		} catch (Exception e) {
			// TODO: handle exception
			
		} finally {
			try {
//				this.rs.close();
				this.ps.close();
				this.con.close();
				
			} catch (SQLException e) {
				System.out.println("e:"+e);
				e.printStackTrace();
			}
		}
		return null;
	}
	

//	@RequestMapping("/event_infook.do")  //=>보안 많이 약함
	@RequestMapping(value="/event_infook.do", method=RequestMethod.POST)  //=>post만 받게 됨  
	public String eventok(event_DTO dto) {
		//eventok(event_DTO dto) => dto로 값 다 받아옴 
		try {
			this.con = this.dbinfo.getConnection();
			String sql = "insert into event values ('0',?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getEname());
			this.ps.setString(2, dto.getEtel());
			this.ps.setString(3, dto.getEmail());
			this.ps.setString(4, dto.getInfo1());
			this.ps.setString(5, dto.getInfo2());
			this.ps.setString(6, dto.getEmemo());
			int result = this.ps.executeUpdate();
			System.out.println(result);
			
		} catch (Exception e) {

		} finally {
			try {
				this.ps.close();
				//this.con은 xml파일에 클로즈 되어있음 
				
			} catch (SQLException se) {
				System.out.println("se : "+ se);
				se.printStackTrace();
			}
		}
		return null; // event_infook.jsp로 전송
	}
	/*@RequestMapping : POST, GET, PUT.... 모든 통신을 다 받을 수 있음. 보안 약함
	  value속성(가상의 경로)와 method 속성(FE와의 데이터 전달방법)을 같이 쓰면 괜춘 */
	
}
