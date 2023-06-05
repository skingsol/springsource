/**
 * 폼 모든 요소가 비어있는지 확인
 *
 */
const form = document.querySelector("#registerForm");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  // form.checkValidity() : 부트스트랩에서 제공하는 함수
  if (!form.checkValidity()) {
    //e.stopPropagation();  이벤트 전파 막기
    form.classList.add("was-validated");
  } else {
    // 첨부파일 정보를 hidden으로 담아서 폼 전송하기
    // 게시글 내용이 작성이 다 되어도 폼 못가게 막기
    // 첨부파일 정보 수집하기
    const lis = document.querySelectorAll(".uploadResult ul li");
    console.log(lis);

    let str = "";
    //lis forEach ==> li 태그 안에 data-속성 값 수집
    lis.forEach((ele, idx) => {
      // console.log("path", ele.dataset.path);
      // console.log("uuid", ele.dataset.uuid);
      // console.log("filename", ele.dataset.filename);
      // console.log("type", ele.dataset.type);
      // li 하나당
      // <input type='hidden' name='attachList[0].uuid' value='cec38c59-f74d-4ae8-a213-7928523ddf8b'/>
      // <input type='hidden' name='attachList[0].uploadPath' value='2023\\05\\30'/>
      // <input type='hidden' name='attachList[0].fileName' value='존윅4.jpg'/>
      // <input type='hidden' name='attachList[0].fileType' value='true'/>

      str +=
        "<input type='hidden' name='attachList[" +
        idx +
        "].uuid' value='" +
        ele.dataset.uuid +
        "'/>";
      str +=
        "<input type='hidden' name='attachList[" +
        idx +
        "].uploadPath' value='" +
        ele.dataset.path +
        "'/>";
      str +=
        "<input type='hidden' name='attachList[" +
        idx +
        "].fileName' value='" +
        ele.dataset.filename +
        "'/>";
      str +=
        "<input type='hidden' name='attachList[" +
        idx +
        "].fileType' value='" +
        ele.dataset.type +
        "'/>";
    });
    //수집한 태그 폼에 추가
    form.insertAdjacentHTML("beforeend", str);
    console.log(form);

    form.submit();
  }
});
