/**
 * 폼 모든 요소가 비어있는지 확인
 *
 */
const form = document.querySelector("#registerForm");

form.addEventListener("submit", (e) => {
  // form.checkValidity() : 부트스트랩에서 제공하는 함수
  if (!form.checkValidity()) {
    e.preventDefault();
    e.stopPropagation();
  }

  form.classList.add("was-validated");
});
