// Toll Collection JS

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('tollForm');
    const resultDiv = document.getElementById('tollResult');

    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        const vehicleId = document.getElementById('vehicleId').value;
        const tollAmount = document.getElementById('tollAmount').value;
        resultDiv.innerHTML = '';
        try {
            const res = await fetch(`/api/toll/pay/${vehicleId}?amount=${tollAmount}`, {
                method: 'POST'
            });
            if (res.ok) {
                const data = await res.json();
                resultDiv.innerHTML = `<div class='alert alert-success'>Toll collected successfully!<br>Vehicle: ${data.vehicle ? data.vehicle.id : vehicleId}<br>Amount: ₹${data.tollAmount}</div>`;
                form.reset();
            } else {
                const err = await res.text();
                resultDiv.innerHTML = `<div class='alert alert-danger'>Failed: ${err}</div>`;
            }
        } catch (err) {
            resultDiv.innerHTML = `<div class='alert alert-danger'>Server error. Please try again.</div>`;
        }
    });
});
