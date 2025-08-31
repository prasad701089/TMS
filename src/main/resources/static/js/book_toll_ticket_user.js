// JS for Book Toll Ticket (User) form submission

document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("bookTollFormUser");
  const successMsg = document.getElementById("ticketSuccessUser");
  const errorMsg = document.getElementById("ticketErrorUser");

  if (!form) return;

  form.addEventListener("submit", async function(e) {
    e.preventDefault();
    if (successMsg) successMsg.classList.add("d-none");
    if (errorMsg) errorMsg.classList.add("d-none");

    const data = {
      branchNo: document.getElementById("branchNo").value,
      state: document.getElementById("state").value,
      nhNumber: document.getElementById("nhNumber").value,
      tollName: document.getElementById("tollName").value,
      tollKm: document.getElementById("tollKm").value,
      section: document.getElementById("section").value,
      vehicleType: document.getElementById("vehicleType").value,
      plan: document.getElementById("plan").value,
      vehicleNumber: document.getElementById("vehicleNumber").value
    };

    try {
      const res = await fetch("/api/toll/ticket", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      });
      if (res.ok) {
        form.reset();
        if (successMsg) successMsg.classList.remove("d-none");
      } else {
        if (errorMsg) errorMsg.classList.remove("d-none");
      }
    } catch (err) {
      if (errorMsg) errorMsg.classList.remove("d-none");
    }
  });
});
