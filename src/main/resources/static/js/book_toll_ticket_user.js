// JS for Book Toll Ticket (User) form submission

document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("bookTollFormUser");
  const successMsg = document.getElementById("ticketSuccessUser");
  const errorMsg = document.getElementById("ticketErrorUser");
  const priceMsg = document.getElementById("ticketPriceUser");

  if (!form) return;

  async function fetchAndShowPrice(vehicleType, plan) {
    if (!vehicleType || !plan) return;
    try {
      const res = await fetch(`/api/toll/price?vehicleType=${encodeURIComponent(vehicleType)}&plan=${encodeURIComponent(plan)}`);
      if (res.ok) {
        const data = await res.json();
        const priceText = document.getElementById("ticketPriceUserText");
        if (priceMsg && priceText) {
          priceText.textContent = `Total Amount of${data.vehicleType} toll (${data.plan}): ₹${data.price}`;
          priceMsg.classList.remove("d-none");
        } else if (priceMsg) {
          priceMsg.textContent = `Toll Price for ${data.vehicleType} (${data.plan}): ₹${data.price}`;
          priceMsg.classList.remove("d-none");
        }
      } else {
        if (priceMsg) priceMsg.classList.add("d-none");
      }
    } catch (err) {
      if (priceMsg) priceMsg.classList.add("d-none");
    }
  }

  // Show price on plan or vehicle type change
  document.getElementById("vehicleType").addEventListener("change", function() {
    fetchAndShowPrice(this.value, document.getElementById("plan").value);
  });
  document.getElementById("plan").addEventListener("change", function() {
    fetchAndShowPrice(document.getElementById("vehicleType").value, this.value);
  });

  form.addEventListener("submit", async function(e) {
    e.preventDefault();
    if (successMsg) successMsg.classList.add("d-none");
    if (errorMsg) errorMsg.classList.add("d-none");
    if (priceMsg) priceMsg.classList.add("d-none");

    const data = {
      //branchNo: document.getElementById("branchNo").value,
      state: document.getElementById("state").value,
      district: document.getElementById("district").value,
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

      // Always show price after submit, regardless of booking success
      fetchAndShowPrice(data.vehicleType, data.plan);
      if (res.ok) {
        // Get price for invoice
        const priceRes = await fetch(`/api/toll/price?vehicleType=${encodeURIComponent(data.vehicleType)}&plan=${encodeURIComponent(data.plan)}`);
        let price = 0;
        if (priceRes.ok) {
          const priceData = await priceRes.json();
          price = priceData.price;
        }
        // Store ticket details in localStorage
        localStorage.setItem("latestTicket", JSON.stringify({ ...data, price }));
        window.location.href = "/ticket_invoice.html";
      } else {
        if (errorMsg) errorMsg.classList.remove("d-none");
      }
    } catch (err) {
      fetchAndShowPrice(data.vehicleType, data.plan);
      if (errorMsg) errorMsg.classList.remove("d-none");
    }
  });
  document.getElementById('logoutBtn').addEventListener('click', function(e) {
  e.preventDefault();
  window.location.href = '/user/login';
  });
});
