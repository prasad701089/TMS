// Reports tab logic for manage_users.html

document.addEventListener('DOMContentLoaded', function() {
    const reportsTab = document.getElementById('reportsTab');
    const usersSection = document.getElementById('usersSection');
    const reportsSection = document.getElementById('reportsSection');
    if (reportsTab && usersSection && reportsSection) {
        reportsTab.addEventListener('click', function(e) {
            e.preventDefault();
            usersSection.style.display = 'none';
            reportsSection.style.display = '';
            loadReports();
        });
    }
});

function loadReports() {
    const reportsContent = document.getElementById('reportsContent');
    reportsContent.innerHTML = '<div class="text-center py-4"><div class="spinner-border text-primary" role="status"></div></div>';
    fetch('/api/toll-reports')
        .then(res => res.json())
        .then(data => {
            if (!data.length) {
                reportsContent.innerHTML = '<div class="alert alert-info">No reports found.</div>';
                return;
            }
            let html = `<div class='table-responsive'><table class='table table-bordered'><thead><tr><th>Date</th><th>Vehicles</th><th>Total Amount</th></tr></thead><tbody>`;
            // Aggregate by date
            const map = {};
            data.forEach(tx => {
                const date = tx.timestamp ? tx.timestamp.split('T')[0] : 'Unknown';
                if (!map[date]) map[date] = { vehicles: 0, amount: 0 };
                map[date].vehicles += 1;
                map[date].amount += tx.tollAmount;
            });
            Object.entries(map).forEach(([date, val]) => {
                html += `<tr><td>${date}</td><td>${val.vehicles}</td><td>₹${val.amount}</td></tr>`;
            });
            html += '</tbody></table></div>';
            reportsContent.innerHTML = html;
        })
        .catch(() => {
            reportsContent.innerHTML = '<div class="alert alert-danger">Failed to load reports.</div>';
        });
}
