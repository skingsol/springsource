/**
 * 기존 비밀번호, 새비밀번호, 새비밀번호확인 내용
 * 새 비밀번호와 새비밀번호 확인 동일한가?
 *
 */
const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
	e.preventDefault();

	const userid = document.querySelector("#userid");
	const currentPassword = document.querySelector("#floatingPassword1");
	const newPassword = document.querySelector("#floatingPassword2");
	const confirmPassword = document.querySelector("#floatingPassword3");
	
	if (currentPassword.value === "") {
		alert("기존 비밀번호를 확인해 주세요");
		currentPassword.select();
		return;
	} else if (newPassword.value === "") {
		alert("변경할 비밀번호를 입력해 주세요");
		newPassword.select();
		return;
	} else if (confirmPassword.value === "") {
		alert("변경할 비밀번호 확인를 해 주세요");
		confirmPassword.select();
		return;
	} else if (currentPassword.value === newPassword.value) {
		alert("기존 비밀번호 제외. 새로운 비밀번호를 입력해 주세요");
		newPassword.select();
		return;
	} else if (newPassword.value !== confirmPassword.value) {
		alert("변경할 비밀번호가 서로 다릅니다.");
		confirmPassword.select();
		return;
	} 
	form.submit();
});
