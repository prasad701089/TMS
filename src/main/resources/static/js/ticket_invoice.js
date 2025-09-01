// JS for Ticket Invoice Page
// This script expects ticket details to be passed via localStorage or query params

document.addEventListener("DOMContentLoaded", function() {
  // Try to get ticket data from localStorage
  let ticket = null;
  try {
    ticket = JSON.parse(localStorage.getItem("latestTicket"));
  } catch {}

  if (ticket) {
    //document.getElementById("invBranchNo").textContent = ticket.branchNo || "";
    document.getElementById("invState").textContent = ticket.state || "";
    document.getElementById("invDistrict").textContent = ticket.district || "";
    document.getElementById("invNhNumber").textContent = ticket.nhNumber || "";
    document.getElementById("invTollName").textContent = ticket.tollName || "";
    document.getElementById("invTollKm").textContent = ticket.tollKm || "";
    document.getElementById("invSection").textContent = ticket.section || "";
    document.getElementById("invVehicleType").textContent = ticket.vehicleType || "";
    document.getElementById("invPlan").textContent = ticket.plan || "";
    document.getElementById("invVehicleNumber").textContent = ticket.vehicleNumber || "";
    document.getElementById("invPrice").textContent = `₹${ticket.price}`;
  }

 


});
