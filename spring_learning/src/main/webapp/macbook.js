function save_class(){
	if(frm.class_name.value==""){
		alert("과정명을 입력하세요");
	}else if(frm.class_day.value==""){
		alert("학습일수를 입력하세요");
	}else if(Number(frm.class_price.value)<0 || frm.class_price.value==""){
		alert("정가를 입력하세요");
	}else if(Number(frm.class_sales.value)<0 || frm.class_sales.value==""){
		alert("수강료를 입력하세요");
	}else{
		frm.submit();
	}
}



window.onload=function(){
	var ea = frm.class_part.length;
//	console.log(frm.class_part[1].value)
	for(var f=0; f<ea; f++){
		if(frm.class_part[f].value==subject){
			frm.class_part[f].selected="selected";
		}
	}
}

 