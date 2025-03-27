package spring_learning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", allowedHeaders = "*")  //Ajex CORS 해결하는 어노테이션
@RestController  //RestController : API구축을 위한 API전용 컨틀롤러 어노테이션
public class api_controller {
	PrintWriter pw = null; //FE가 값을 가져갈수 있도록 함 
	
	//1차배열, 1차키배열
	@GetMapping("/api_data.do")
	public String api_data(HttpServletResponse res) throws IOException{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		/*  //1차배열형태
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d"); 
		
		this.pw.print(ja);
		*/
		
		/* //1차 키배열형태
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		
		this.pw.print(jo);
		*/
		
		/* JSONArray : [] (키없음. 1차배열형태)
		   JSONObject : {} (키를생성. 키 있는 1차배열형태)
		   .put("키", 넣을 값)  => {"키":["a","b","c","d"]}
		 	
		  ※org.json 라이브러리 => put으로 데이터 넣음 
		   org.json.simple 라이브러리 => put이 아니라 add 로 데이터 넣음 
		 */
		
		this.pw.close();
		return null;
	}
	
	
	//2차 키배열 
	@GetMapping("/api_data2.do")
	public String api_data2(HttpServletResponse res)throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		/*
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		
		JSONArray ja2 = new JSONArray();
		ja2.put(ja);

		this.pw.print(ja2);
		*/
		
		/*
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
		
		JSONArray ja2 = new JSONArray();
		ja2.put(jo);
		
		this.pw.print(ja2);  //맨마지막 JSON객체를 출력
		*/
		
		this.pw.close();
		return null;
	}
	
	
	//db값 끌고오기 
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse res)throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		String db[] = {"hong","홍길동","hong@nate.com","서울","01012345678"};
		
		JSONObject jo = new JSONObject();
		jo.put("id", db[0]);
		jo.put("name", db[1]);
		jo.put("email", db[2]);
		jo.put("area", db[3]);
		jo.put("phone", db[4]); 
		//JSON은 순서 랜덤으로 나올 수 있음 
		
//		JSONArray ja = new JSONArray(jo);
		JSONArray ja = new JSONArray();
		ja.put(jo);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("myinfo", ja);
		
		this.pw.print(jo2);   //FE에서 출력

		this.pw.close();
		return null;
	}
	
	
	//응용문제
	@GetMapping("/api_data4.do")
	public String api_data4(HttpServletResponse res)throws Exception {
		/*res.addHeader("Access-Control-Allow-Origin", "*");   
		res.addHeader("Access-Control-Allow-Credentials", "true");*/
		//↑도메인이 틀려도 해당 API로 접속 가능 (CORS 해결) => 서블릿 방식 형태 
		//스프링.스프링부트는 클래스에 @붙여서 만듦 => @CrossOrigin(origins="*", allowedHeaders = "*")
		
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		String db[][] = {{"모니터","키보드","마우스"},{"NEW","BEST","NEW"}};

		int w=0;
		String subkey=""; //서브키
		JSONObject alldata = new JSONObject();  //대표키 만드는 애
		JSONObject jo = new JSONObject();  //보조키(서브키) 만드는애 
		while(w<db.length) {
			
			JSONArray ja = new JSONArray();  //데이터 배열[]
			
			for(int f=0; f<db[w].length; f++) {
				ja.put(db[w][f]);  //데이터 배열을 생성 
			}
			
			//데이터배열에 맞는 보조키를 적용하기 위한 조건문 
			if(w==0) {
				subkey = "product_name";
			}else {
				subkey = "product_ico";
			}
			
			jo.put(subkey, ja);
			w++;
		}
		alldata.put("products",jo);  //대표키생성은 최종반복문 밖에 씀
		this.pw.print(alldata);

		this.pw.close();
		return null;
	}
	
	
	//진짜 db값 끌고와서 api 생성 
	@Resource(name="memberDTO")macbook_member_DTO m_dto;
	@Resource(name="memberDAO")macbook_member_DAO m_dao;
	@GetMapping("/api_data5.do")
	public String api_data5(HttpServletResponse res)throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		List<macbook_member_DTO> result = this.m_dao.all_list();
		JSONObject alldata = new JSONObject(); //{}대표키
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();  //[]
		int w = 0;
		while(w<result.size()) {
			jo.put("midx",result.get(w).midx);
			jo.put("mid",result.get(w).mid);
			jo.put("mname",result.get(w).mname);
			jo.put("memail",result.get(w).memail);
			
			ja.put(jo);
			w++;
		}
		alldata.put("member", ja);
		this.pw.print(alldata);
		
		this.pw.close();
		return null;
	}
	
	
}
