// JS for staff_dashboard.html
// Replace with real API calls as needed

document.addEventListener('DOMContentLoaded', function() {
    // Simulate fetching stats
    setTimeout(() => {
        document.getElementById('tollToday').textContent = 1200;
        document.getElementById('vehicleCount').textContent = 45;

        // Simulate recent transactions
        const transactions = [
            'Car - Paid ₹100',
            'Truck - Paid ₹250',
            'Bus - Paid ₹180',
            'Car - Paid ₹100'
        ];
        const transactionList = document.getElementById('transactionList');
        transactionList.innerHTML = '';
        transactions.forEach(tx => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = tx;
            transactionList.appendChild(li);
        });
    }, 500);
});
