document.addEventListener('DOMContentLoaded', function() {
	let reports = [];

	// Fetch real data from backend
		fetch('/api/toll/reports')
		.then(response => response.json())
		.then(data => {
			reports = aggregateReports(data);
			renderTable(reports);
		})
		.catch(err => {
			console.error('Failed to fetch toll reports:', err);
		});

	// Helper to aggregate transactions by date
	function aggregateReports(data) {
		const map = {};
		data.forEach(tx => {
				const date = tx.createdAt ? tx.createdAt.split('T')[0] : 'Unknown';
			if (!map[date]) map[date] = { date, vehicles: 0, amount: 0 };
			map[date].vehicles += 1;
				map[date].amount += tx.amount || 0;
		});
		return Object.values(map).sort((a, b) => b.date.localeCompare(a.date));
	}


	// Render table and summary
	function renderTable(data) {
		const tbody = document.getElementById('tollReportTable');
		tbody.innerHTML = '';
		let totalVehicles = 0, totalAmount = 0;
		data.forEach((r, idx) => {
			const tr = document.createElement('tr');
			tr.innerHTML = `
				<td>${idx + 1}</td>
				<td>${r.date}</td>
				<td><span class="badge">${r.vehicles}</span></td>
				<td><span class="badge amount-badge">₹${r.amount}</span></td>
			`;
			tbody.appendChild(tr);
			totalVehicles += r.vehicles;
			totalAmount += r.amount;
		});
		document.getElementById('totalVehicles').textContent = totalVehicles;
		document.getElementById('totalAmount').textContent = `₹${totalAmount}`;
	}

	// Initial render (empty, will be filled after fetch)
	renderTable(reports);

	// Optionally, you can add filter/export logic here if needed
});
