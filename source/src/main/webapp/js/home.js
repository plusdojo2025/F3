 window.addEventListener("DOMContentLoaded", () => {
	const hour_time = new Date().getHours();
    if ((type.trim().length > 0) && currentDate === "1970-01-01" && hour_time < 18) {
      document.getElementById("checkModal").style.display = "block";
    }
  });