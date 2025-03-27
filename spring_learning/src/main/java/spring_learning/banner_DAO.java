package spring_learning;

import java.util.List;

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
	public List<banner_DTO> allbanner() {
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all");
		return all;
	}

}
