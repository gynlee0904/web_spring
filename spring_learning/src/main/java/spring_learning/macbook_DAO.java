package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//DAO : 데이터를 Access하는 모델 (mybatis에서 사용하는 모델. 여기서 db연결)
@Repository("macbook_DAO")  //Repository() : 컨트롤러에서 모델을 호출할수 있도록하는 @
public class macbook_DAO implements macbook_mapper {
	
	@Resource(name="template")  //mybatis로 db연결  
	public SqlSessionTemplate st;
	//java클래스에서 가져올떄는 Repository랑 쌍쌍이어야 하지만 xml의 id값을 가져올때는 Repository없어도 id랑 맞기만 하면 됨 
	
	@Override
	public List<macbook_DTO> macbook_select() {
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		/*.selectOne : 데이터 하나만 들고올 때 사용 (자료형은 ArrayList 혹은 List 혹은 DTO로 받음)
		  .selectList : 데이트를 여러개 가져올 때 사용 (자료형은 List<>배열로 받아야함)
		 */
		return classList;
	}
	
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert",dto);
		return result;
	}
	
	
	@Override
	public macbook_DTO macbook_one(String midx) {
		//setter형태로 DB에 있는 데이터를 전달 
		macbook_DTO classOne = this.st.selectOne("macbook_one",midx);
		//selectOne("mapper.xml의 id명", 매개변수)
		return classOne;
	}
	
	@Override
	public int macbook_modify(macbook_DTO dto) {
		int result = this.st.update("macbook_modify", dto);
		return result;
	}
	
	
}
