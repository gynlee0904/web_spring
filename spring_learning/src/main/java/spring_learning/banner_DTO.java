package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Data;


@Data   //@Data : @Setter, @Getter 같이 있는 어노테이션
@Repository("banner_DTO")
public class banner_DTO {
//DTO 생성시 config.xml에 꼭 추가할것!!
	int bidx;
	String bname, file_orinm, file_renm, file_url, bdate;
}
