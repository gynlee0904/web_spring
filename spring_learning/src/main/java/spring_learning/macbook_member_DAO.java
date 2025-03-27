package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class macbook_member_DAO {
	
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	
	
	//@Mapper 인터페이스 없이 member_mapper.xml에서 메퍼네임스페이스명.태그아이디를 결합해 쿼리문 작성(권장no). 정석은 인터페이스 써서 해야함 
	public List<macbook_member_DTO> all_list(){
		List<macbook_member_DTO> all = this.st.selectList("macbook_user.user_all");
		//selectList("메퍼네임스페이스명.태그아이디")
//		System.out.println(all.get(0).memail);
		return all;
	}
	
	public macbook_member_DTO user_search(String name, String email) {
		Map<String, String> code = new HashMap<String, String>();
		code.put("mname",name);  //""안에는 mapper의 #{}안의 키와 맞춤
		code.put("memail",email);
		
		macbook_member_DTO userid = this.st.selectOne("macbook_user.search_userid",code);
		
		System.out.println("userid : "+userid);
		return userid;
	}

}
