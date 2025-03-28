package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	List<String> listdata = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;
	
	@Resource(name="banner_DTO")banner_DTO dto;  //필드에 있는 dto는 네임값이 없음. 허허벌판임. 값세팅을 다시 해야할 떄 사용
	@Resource(name="banner_DAO")banner_DAO dao;
	@Resource(name="filerename")banner_m_filerename fname;  //파일명 바꾸기 위한 모델 
	
	/*필드에 있는 dto와 메소드 매개변수에 있는 dto는 다른거임. 
	  this.dto => 필드에 있는 dto
	  dto => 매개변수에 있는 dto 
	*/
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name="dto") banner_DTO dto, //=>네임값을 가지고 있는 dto
							MultipartFile bfile,
							HttpServletRequest req,
							Model m) throws Exception {
		//@RequestParam : 특정 하나의 네임값만 받음
		/*@ModelAttribute : DTO 전용 @. 
		  1대1매칭 가능(name값과 dto변수가 같은것이 있으면 무조건 값을 넣어줌)
		  boot에서 많이 씀
		 */
		String file_renm = null; 
		if(bfile.getSize()>0) {  //파일이 첨부된 경우(용량이 있음) 
			file_renm = this.fname.rename(bfile.getOriginalFilename());
			dto.setFile_renm(file_renm);  //새로 붙인 파일명 
			dto.setFile_orinm(bfile.getOriginalFilename());  //사용자가 첨부한 원래파일명
			
			String url = req.getServletContext().getRealPath("/file/");
//			System.out.println(url);
			
			FileCopyUtils.copy(bfile.getBytes(),new File(url+file_renm));  //웹디렉톨에 리네임한 파일명으로 저장 
			dto.setFile_url("/file/"+file_renm);  //웹디렉토리 경로+리네임 파일명을 db에 저장 
		}

		this.callback = this.dao.new_banner(dto);
		
		String msg="alert('등록완료'); location.href='./bannerlist';";
		m.addAttribute("msg",msg);

		return "load";
	}
	
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m,
							@RequestParam(defaultValue="", required=false)String search,
							@RequestParam(defaultValue="1", required=false)Integer pageno) throws Exception {
	//(defaultValue="", required=false) : 검색(search)에 관한 사항은 필수가 아니며 null로 날아온 경우 값을 공백으로 처리한다는 뜻 	
		
		
		//데이터 총 개수 확인 코드 
		int total = this.dao.banner_total();
		int userPage = 0;  //사용자가 클릭한 페이지번호에 맞는 게시글 순차번호 계산값
		if(pageno ==1) {
			userPage = 0;
		}else {  //1외의 다른 페이지를 클릭시
			userPage = (pageno -1)*5;
		}
		
		List<banner_DTO> all = null;
		if(search.equals("")) { //검색어가 없을 경우
			all = this.dao.allbanner(pageno);  //인자값 => 사용자가 클릭한 페이지번호 전달
			
		}else {  //검색어가 있을 경우 
			all = this.dao.searchbanner(search);
		}
		
		m.addAttribute("userPage",userPage);  //해당 일련번호를 계산하여 jsp에 전달
		m.addAttribute("total",total);  //데이터 전체개수 
		m.addAttribute("search",search);
		m.addAttribute("all",all);
		
		return null;
	}
	
	
	
}
