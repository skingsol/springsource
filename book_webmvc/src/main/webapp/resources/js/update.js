/**
 * 
 */

 
 document.querySelector(".btn-primary").addEventListener("click", () => {
  location.href = listpath;
});

 document.querySelector(".btn-danger").addEventListener("click", () => {
  location.href = removepath+"?code="+code;
});