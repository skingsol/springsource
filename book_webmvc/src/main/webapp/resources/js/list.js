/**
 * 검색기준 인 writer이나 title 이 선택 됬는지 확인
 * 검색어가 입력이 되었는지 확인
 * 둘다 입력된 경우에만 submit
 * 둘다 비어있으면 submit 막기
 * 
 * /serch. do 로 보내기
 * 
 * action, Servic, DAO 만들기
 */

 const form = document.querySelector("form");

  form.addEventListener("submit", (e) => {
	  e.preventDefault();
	  
    const sel = document.querySelector("[name=criteria]");
	const keyword = document.querySelector("[name=keyword]");
	
	//select 요소의 선택한 value 가져오기
	//const selectedVal = sel.options[sel.selectedIndex].value;
	//console.log(selectedVal);
	console.log(sel.value);
	
	
	if(sel.value === "검색기준선택"){
		alert("검색기준을 선택해 주세요");
		sel.focus();
		return;
	}else if(keyword.value === ""){
		alert("검색어을 확인해 주세요");
		keyword.select();
		return;
	}
	form.submit();	
		
  })