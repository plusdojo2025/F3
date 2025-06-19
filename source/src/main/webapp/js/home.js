window.addEventListener("DOMContentLoaded", () => {
  if (currentDate === "1970-01-01" && hour_int < 10) {
    document.getElementById("checkModal").style.display = "block";
  }
});