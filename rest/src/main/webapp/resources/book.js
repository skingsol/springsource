/**
 * 버튼 클릭 시 도서 전체 목록 가져오기
 *
 * 화면 깜빡임 없이 데이터 가져오기 ==> ajax
 *
 * ajax : fetch, jquery, axios
 */

document.querySelector(".btn-primary").addEventListener("click", () => {
  //fetch("경로").then(서버로부터 데이터 받기(적절한 타입으로 변환)).then(화면출력).catch(에러);

  fetch("/list")
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      //내용 보여줄 위치 가져오기
      const content = document.querySelector(".table tbody");
      let str = "";
      data.forEach((item) => {
        str += "<tr>";
        str += "<th scope='row'>" + item.code + "</th>";
        str += "<td>" + item.title + "</td>";
        str += "<td>" + item.writer + "</td>";
        str += "<td>" + item.price + "</td>";
        str += "</tr>";
      });
      content.innerHTML = str;
    })
    .catch((error) => console.log(error));
});
