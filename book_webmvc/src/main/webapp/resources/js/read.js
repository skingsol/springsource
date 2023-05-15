/**
 * 
 */

 
 document.querySelector(".btn-primary").addEventListener("click", () => {
  location.href = listpath;
});

 document.querySelector(".btn-success").addEventListener("click", () => {
  location.href = modifypath+"?code="+code;
});