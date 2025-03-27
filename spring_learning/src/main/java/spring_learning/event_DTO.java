package spring_learning;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class event_DTO {
	int eidx;
	String ename, etel, email, info1, info2, ememo, ejoin;
	//DTO의 변수명은 db의 컬럼명, FE의 name명과 일치시킨다.
	
}
