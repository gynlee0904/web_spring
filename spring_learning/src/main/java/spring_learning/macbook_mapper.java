package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//@Mapper : mapper.xml의 namespace와 Mapper 인터페이스를 결합(연동) + mapper.xml의 sql태그 id와 연동 
//sql쿼리문을 실행시키기위해 생성한 인터페이스임.
//★★mapper.xml에서 사용하는 id값으로 메소드명이 정해짐.
//Controller -> Mapper -> service -> Service -> mapper.xml -> 
@Mapper
public interface macbook_mapper {  
//@Mapper의 역할은 mapper.xml과 연결시켜주는 인터페이스인것임.
	public int macbook_insert(macbook_DTO dto);  //메소드명 => mapper.xml의 sql태그의 id
	//절대 private로 만들지 않는다 
	
	List<macbook_DTO> macbook_select();  //전체데이터 가져오는 메소드
	
	macbook_DTO macbook_one(String midx);  //하나의 데이터만 가져오는 메소드
	
	int macbook_modify(macbook_DTO dto); //데이터 수정 메소드 
	
	

	
	
}
