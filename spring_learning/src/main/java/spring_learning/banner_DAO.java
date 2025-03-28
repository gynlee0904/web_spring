package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("banner_DAO")
public class banner_DAO {
	
	@Resource(name="template") SqlSessionTemplate st;
	
	//신규배너 등록 메소드
	public int new_banner(banner_DTO dto) {
		int result = this.st.insert("macbook_user.banner_in",dto);
		return result;
	}
	
	//배너 전체 데이터
//	public List<banner_DTO> allbanner() {
//		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all");
//		return all;
//	}
	//배너 전체 데이터 + 페이징 추가
	Integer page_ea = 5;  //한페이지당 출력할 개수
	public int banner_total() {
		int total = this.st.selectOne("macbook_user.banner_total");
		return total;
	}
	
	public List<banner_DTO> allbanner(Integer pgno) {  //Integer pgno : 컨트롤에서 사용자가 클릭한 페이지 번호를 받는 역할 
		/* 사용자가 1 페이지 클릭 => limit 0,5
		   사용자가 2 페이지 클릭 => limit 5,5
		   사용자가 3 페이지 클릭 => limit 10,5
		*/
		int start_p = (pgno-1)*this.page_ea;  //limit 시작번호
		
		Map<String, Integer> page = new HashMap<String, Integer>();  //쿼리문에서 limit을 사용하기 위해 MAP 형태로 구성하여 mapper로 넘김 
		page.put("start_p" , start_p);  //limit의 첫번쨰 번호 
		page.put("page_ea" , this.page_ea);  //limit의 두번째 번호
		
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all",page);
		return all;
	}

	public List<banner_DTO> searchbanner(String search) {
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_search", search);
		return all;
	}

}
