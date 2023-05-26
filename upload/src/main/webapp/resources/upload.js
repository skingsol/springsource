/**
 *
 */
document.querySelector("#uploadBtn").addEventListener("click", () => {
  //스크립트에서 폼을 만드는 방식
  //FormData 객체 생성
  const formData = new FormData();

  //file 요소 가져오기
  let inputFiles = document.querySelector("#uploadFile").files;
  console.log(inputFiles);

  //가져온 file 요소를 formData 추가
  for (let i = 0; i < inputFiles.length; i++) {
    formData.append("uploadFile", inputFiles[i]);
  }

  // 비동기 - formData 전송
  fetch("/uploadAjax", {
    method: "post",
    body: formData,
  })
    .then((response) => {
      if (!response) {
        throw new Error("파일 업로드 실패");
      }
      return response.text();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => console.log(error));
});
