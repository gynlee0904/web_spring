package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Repository("memberDTO")
public class macbook_member_DTO {
	int midx;
	String mid, mname, memail;

}
