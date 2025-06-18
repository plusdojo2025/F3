const hour_int = Number(hour); // 小数も扱えます（今回は整数なので問題なし）

window.addEventListener("DOMContentLoaded", () => {
  if (currentDate === "1970-01-01" && hour_int < 10) {
    document.getElementById("checkModal").style.display = "block";
  }
});
