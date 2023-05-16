/**
 * 폼 모든 요소가 비어있는지 확인
 * 하나라도 비어있다면 submit 막기
 * 
 * document.querySelector(".btn-success").addEventListener("click", () => location.href = path);

const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
	e.preventDefault();

	const userid = document.querySelector("#userid");
	const password = document.querySelector("#password");
	const name = document.querySelector("#name");
	const gender = document.querySelector('input[name="gender"]:checked');
	const email = document.querySelector("#email");
	const useridRegex = /^[^\uAC00-\uD7A3]{1,12}$/;
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{1,15}$/;
	const emailRegex = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;

	if (userid.value === "") {
		alert("아이디를 확인해 주세요");
		userid.select();
		return;
	} else if (!useridRegex.test(userid.value)) {
		alert("아이디는 한글을 제외하고 최대 12자 이내로 입력해주세요");
		userid.select();
		return;
	} else if (password.value === "") {
		alert("비밀번호를 확인해 주세요");
		password.select();
		return;
	} else if (!passwordRegex.test(password.value)) {
		alert("비밀번호는 영문, 숫자, 특수문자를 최소 1개 이상 포함하고 15자 이내로 입력해주세요");
		password.select();
		return;
	} else if (name.value === "") {
		alert("이름을 확인해 주세요");
		name.select();
		return;
	} else if (gender.value === null) {
		alert("성별을 선택해 주세요");
		return;
	} else if (email.value === "") {
		alert("이메일 주소를 확인해 주세요");
		email.select();
		return;
	} else if (!emailRegex.test(email.value)) {
		alert("유효한 이메일 주소를 입력해 주세요");
		email.focus();
		return;
	}
	form.submit();
});
 */

document.querySelector(".btn-success").addEventListener("click", () => location.href = path);

const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
	if(!form.checkValidity()){
		e.preventDefault();
		e.stopPropagation();		
	}

	form.classList.add("was-validated");
})

document.querySelector(".btn-danger").addEventListener('click', ()=>{
	//사용자가 입력한 userid 가져오기
	const userid = document.querySelector("#userid").value;
	
	
	fetch("dupId.do",{
		method: "post",
		body: new URLSearchParams({userid:userid})
		
	})
	.then((response)=> response.text())
	.then((result)=> {
		console.log(result);
		if(result.trim() === "true"){
			alert("아이디를 사용할 수 있습니다.");
		}else{
			alert("아이디를 사용할 수 없습니다.")
		}
		});
})