package spring_learning;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

//IO관련 컨트롤러 
@Controller
public class mainpage03 {
/*Spring은 @MultipartConfig 안씀. 대신 메소드()안에 작성
  Part도 안씀 */
	
	//하나의 첨부파일을 전송받는 메소드
	@PostMapping("/fileok.do")
	public String fileupload(MultipartFile mfile) {  //mfile => FE에서 넘어오는 name값과 일치시킬것 
	//MultipartFile 인터페이스 : SpringIO xml환경설정과 연결됨
//		System.out.println(mfile.getOriginalFilename());  //파일명 가져올 수 있음 
		if(mfile.getSize() > 2097152) {  //첨부파일이 2MB이상일 경우 어떻게 할지 제어 
			System.out.println("test");
		}
		
		return "load";
	}
	
	
	//여러개의 첨부파일을 전송받는 메소드
	@PostMapping("/fileok2.do")
	public String fileupok(MultipartFile[] mfile, HttpServletRequest req) throws IOException {
//	public String fileupok(MultipartFile mfile, MultipartFile mfile2) => name값이 다르면 이렇게 각각 써야함
		
//		System.out.println(mfile[0].getOriginalFilename());  //첫번째 파일의 파일명
//		System.out.println(mfile[1].getOriginalFilename());  //두번째 파일의 파일명
//		System.out.println(mfile[2].getOriginalFilename());  
		
		String url = req.getServletContext().getRealPath("/file_upload/");
//		System.out.println(url);
		
		int w = 0;
		while(w < mfile.length) {
			if(mfile[w] != null && !mfile[w].isEmpty()){  
			/*파일첨부창이 여러개인경우 mfile.length는 파일첨부창 개수만큼임. 
			  null값이 있을수 있기 때문에 if문으로 제어해줘야함
			  FE쪽 input태그에 multiple이 붙어있으면(=>하나의 첨부파일창으로 여러개의 파일 받을때) if문 필요없음! */
				
				//스프링전용 저장방식 => FileCopyUtils.copy(파일크기(=>바이트),new File(저장경로+파일명)) 라이브러리 사용
				FileCopyUtils.copy(mfile[w].getBytes(), new File(url+mfile[w].getOriginalFilename()));
				//=>IO니까 try~catch 쓰거나 메소드에 throws IOException 작성 	
			}
			w++;
		}
		
		
		return "load";
	}
	
	
	//웹디렉토리에 있는 파일리스트를 출력하는 컨트롤러 
	@GetMapping("/filelist.do")
	public String filelist(HttpServletRequest req) throws IOException {
		String url = req.getServletContext().getRealPath("/file_upload/");  //웹디렉토리 경로 
		File f = new File(url); 

		String f_list[] = f.list();  //원시배열 만들어서 웹디렉토리에 있는 파일리스트 저장 
//		System.out.println(f_list[0]);  //첫번째 파일의 파일명 가져옴 
		
		ArrayList<String> filenm = new ArrayList<String>(Arrays.asList(f_list)); //웹디렉토리에 저장된 모든 파일의 파일명을 담기위한 배열 
		req.setAttribute("filenm", filenm);

		return null;
	}
	
	
	//파일 삭제 컨트롤러 
	@PostMapping("/filedel.do")
	public String filedelete(String fnm,HttpServletRequest req, Model m) throws IOException {  
	/*(@RequestParam("fnm") String filenm)에서 @RequestParam이 생략 
	 	=> String변수명은 FE의 name값과 일치시켜야함
	   @RequestParam : FE에서 전달된 값. request.getParameter()과 같은것
	 */
		String url = req.getServletContext().getRealPath("/file_upload/");
		File f = new File(url + fnm);
		f.delete();  //파일삭제
		
		//JS메세지를 전달되는 jsp페이지로 전달
		String msg = "alert('파일삭제 완료');"+"location.href='./filelist.do';";
		m.addAttribute("msg",msg);
		
		return "load";
	}
	
	
	//DTO 사용시
//	@PostMapping("/fileok2.do")
//	public String fileupok2(file_DTO dto) {  
//		System.out.println(dto.getMfile()); 
//		//DTO로 쓰면 파일명은 못가져옴 (복잡해짐)
//		//스프링은 IO처리시 DTO단독으로 사용 안함!!(의미가없음)
//	public String fileupok2(file_DTO dto, MultipartFile[] mfile) => dto에는 나머지 값 받고 파일은 따로 뺌 
//		return "load";
//	}
	
	
	//컨트롤 통해 jstl 로드 후 값 전달 
	@GetMapping("/jstl/jstl06.do")
	public String jstl06(Model m) {
		String level = "일반수강생"; 
		String corp = "산왕컴퍼니"; 
		String tel = "07009040713"; 
		
		m.addAttribute("level", level);
		m.addAttribute("corp", corp);
		m.addAttribute("tel", tel);
		//모델을 이용하여 jstl06.jsp로 값 전달 
		//do로 접속시 jstl_top.jsp에서 ${}로 바로 받을 수 있음  
		return null;
	}
	

}
