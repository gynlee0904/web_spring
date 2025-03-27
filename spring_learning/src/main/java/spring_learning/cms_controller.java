package spring_learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class cms_controller {
/*DTO를 꼭 안만들어도됨. DTO가 무조건 필요할 경우는 리스트를 출력해야하는 경우.*/
	
	//DAO가 없는경우 컨트롤러에 DB연결 쓸 수도 있음(MVC무시. 권장no)
	@Resource(name="template")  
	public SqlSessionTemplate st;
	
	/*FE디렉토리와 BE패키지가 다른경우 
	 * FE에서 ./이름.do 로 입력한경우 => 앞에 /FE디렉토리/ 붙이면 됨
	 * FE에서 /이름.do 로 입력한경우 => 플젝우클릭 > 환경설정 > 프로젝트 세팅즈에서 context root / 로 바꿔야 함 
	 * FE에서 ../이름.do 로 입력한경우 => /만 입력하면 됨
	 */
	@PostMapping("/macbook_cms/cmsok.do")
	public String cmsok(@RequestParam String subject,
						@RequestParam String cuser,
						@RequestParam (name="cate", required=false) ArrayList<String> cate) throws Exception{
		//ArrayList로 받은 이유 : 클래스배열로 동일한 체크박스를 처리함
		//DTO에서는 그냥 스트링으로 받음
		
//		System.out.println("cate : " + cate); //=>배열 
		String catein = String.join(",", cate);
//		System.out.println("catein : " + catein);  //=>문자열
		/*체크박스 동일한 name이 여러개 있을 경우 배열로 값을 받으며, 
		  DB에 값을 저장시 String으로 변환하여 
		  String.join()클래스를 이용하여 DB에 set이라는 자료형으로 저장함.
		*/
		
		Map<String, String> data= new HashMap<String, String>();
		data.put("subject", subject);
		data.put("cuser", cuser);
		data.put("cate", catein);
		
		//mapper.xml에 다른 테이블을 사용하더라도 문제는 안됨. 단, 유지보수 힘들어짐(쿼리문 찾기 힘듦)
		int result = this.st.insert("macbook_user.cms_in",data);
		System.out.println("result : "+result); //1 출력 (인서트한 개수)
		
		return "load";
	}
	
	//cms상담신청내역 상세페이지 
	@GetMapping("/macbook_cms/cmsview.do")
	public String cmsview(Model m) throws Exception{
//		String result = this.st.selectOne("macbook_user.cms_view");
//		System.out.println("result2 : " + result);  //select한 컬럼의 결과값 가져옴 (컬럼이 여러개이면 제일 처음 값 하나만 가져옴)

//		Map<String, String> result = this.st.selectOne("macbook_user.cms_view");
//		System.out.println("result2 : " + result);  //select 컬럼값 다 가져옴 
		
//		List<String> result = this.st.selectList("macbook_user.cms_view");
//		System.out.println("result2 : " + result);  //select 컬럼값 다 가져옴 
		
		List<Map<String, String>> result = this.st.selectList("macbook_user.cms_view");
		System.out.println("result2 : " + result);  //select 컬럼값 다 가져옴 
		System.out.println("cate : " + result.get(0).get("cate"));
		
//		m.addAttribute("result",result);
		m.addAttribute("subject",result.get(0).get("subject"));
		m.addAttribute("cuser",result.get(0).get("cuser"));
		m.addAttribute("cate",result.get(0).get("cate"));
		return null;
	}
	

}
