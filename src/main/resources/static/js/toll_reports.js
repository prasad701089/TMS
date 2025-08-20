
// Example: Fetch and display toll reports (replace with real API call)

document.addEventListener('DOMContentLoaded', function() {
	// Simulated data (replace with API call for real data)
	let reports = [
		{ date: '2025-08-19', vehicles: 120, amount: 12000 },
		{ date: '2025-08-18', vehicles: 98, amount: 9800 },
		{ date: '2025-08-17', vehicles: 110, amount: 11000 },
		{ date: '2025-08-16', vehicles: 105, amount: 10500 }
	];

	let container = document.querySelector('.container');

	// Filter UI
	const filterDiv = document.createElement('div');
	filterDiv.className = 'row mb-3';
	filterDiv.innerHTML = `
		<div class="col-md-4 mb-2">
			<input type="date" class="form-control" id="fromDate" placeholder="From Date">
		</div>
		<div class="col-md-4 mb-2">
			<input type="date" class="form-control" id="toDate" placeholder="To Date">
		</div>
		<div class="col-md-4 mb-2 d-flex gap-2">
			<button class="btn btn-primary flex-fill" id="filterBtn">Filter</button>
			<button class="btn btn-secondary flex-fill" id="resetBtn">Reset</button>
			<button class="btn btn-success flex-fill" id="exportBtn">Export CSV</button>
		</div>
	`;
	container.appendChild(filterDiv);

	// Table
	let table = document.createElement('table');
	table.className = 'table table-bordered mt-2';
	table.innerHTML = `
		<thead class="table-light">
			<tr>
				<th>Date</th>
				<th>Vehicles</th>
				<th>Total Amount (₹)</th>
			</tr>
		</thead>
		<tbody></tbody>
		<tfoot><tr><th>Total</th><th id="totalVehicles"></th><th id="totalAmount"></th></tr></tfoot>
	`;
	container.appendChild(table);

	function renderTable(data) {
		const tbody = table.querySelector('tbody');
		tbody.innerHTML = '';
		let totalVehicles = 0, totalAmount = 0;
		data.forEach(r => {
			const tr = document.createElement('tr');
			tr.innerHTML = `
				<td>${r.date}</td>
				<td>${r.vehicles}</td>
				<td>${r.amount}</td>
			`;
			tbody.appendChild(tr);
			totalVehicles += r.vehicles;
			totalAmount += r.amount;
		});
		document.getElementById('totalVehicles').textContent = totalVehicles;
		document.getElementById('totalAmount').textContent = totalAmount;
	}

	// Initial render
	renderTable(reports);

	// Filter logic
	document.getElementById('filterBtn').onclick = function() {
		const from = document.getElementById('fromDate').value;
		const to = document.getElementById('toDate').value;
		let filtered = reports.filter(r => {
			return (!from || r.date >= from) && (!to || r.date <= to);
		});
		renderTable(filtered);
	};
	document.getElementById('resetBtn').onclick = function() {
		document.getElementById('fromDate').value = '';
		document.getElementById('toDate').value = '';
		renderTable(reports);
	};

	// Export CSV
	document.getElementById('exportBtn').onclick = function() {
		const rows = [['Date','Vehicles','Total Amount (₹)']];
		const tbody = table.querySelector('tbody');
		for (let tr of tbody.rows) {
			let row = [];
			for (let td of tr.cells) row.push(td.textContent);
			rows.push(row);
		}
		let csv = rows.map(r => r.join(",")).join("\n");
		let blob = new Blob([csv], {type: 'text/csv'});
		let a = document.createElement('a');
		a.href = URL.createObjectURL(blob);
		a.download = 'toll_reports.csv';
		a.click();
	};
});
