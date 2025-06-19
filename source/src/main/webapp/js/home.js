const hour = new Date().getHours();

window.addEventListener("DOMContentLoaded", () => {
  if (currentDate === "1970-01-01" && hour < 18) {
    document.getElementById("checkModal").style.display = "block";
  }
});