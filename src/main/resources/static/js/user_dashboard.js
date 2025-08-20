
// JS for user_dashboard.html
// Sidebar navigation and section switching
document.addEventListener('DOMContentLoaded', function() {
	const dashboardBtn = document.getElementById('dashboardBtn');
	const profileBtn = document.getElementById('profileBtn');
	const transactionsBtn = document.getElementById('transactionsBtn');
	const dashboardSection = document.getElementById('dashboardSection');
	const profileSection = document.getElementById('profileSection');
	const transactionsSection = document.getElementById('transactionsSection');

	function showSection(section) {
		if (dashboardSection) dashboardSection.style.display = 'none';
		if (profileSection) profileSection.style.display = 'none';
		if (transactionsSection) transactionsSection.style.display = 'none';
		if (section) section.style.display = '';
		// Highlight active
		document.querySelectorAll('.nav-link').forEach(l => l.classList.remove('active'));
		if (section === dashboardSection) dashboardBtn.classList.add('active');
		if (section === profileSection) profileBtn.classList.add('active');
		if (section === transactionsSection) transactionsBtn.classList.add('active');
	}

	if (dashboardBtn && dashboardSection) dashboardBtn.addEventListener('click', function(e) { e.preventDefault(); showSection(dashboardSection); });
	if (profileBtn && profileSection) profileBtn.addEventListener('click', function(e) { e.preventDefault(); showSection(profileSection); });
	if (transactionsBtn && transactionsSection) transactionsBtn.addEventListener('click', function(e) { e.preventDefault(); showSection(transactionsSection); });

	// Simulate fetching stats
	setTimeout(() => {
		if (document.getElementById('tollsPaid')) document.getElementById('tollsPaid').textContent = 8;
		if (document.getElementById('amountPaid')) document.getElementById('amountPaid').textContent = '₹800';

		// Simulate recent transactions
		const transactions = [
			'2025-08-19: Paid ₹100',
			'2025-08-17: Paid ₹100',
			'2025-08-15: Paid ₹100',
			'2025-08-12: Paid ₹100'
		];
		const transactionList = document.getElementById('userTransactionList');
		if (transactionList) {
			transactionList.innerHTML = '';
			transactions.forEach(tx => {
				const li = document.createElement('li');
				li.className = 'list-group-item';
				li.textContent = tx;
				transactionList.appendChild(li);
			});
		}

		// Simulate all transactions for My Transactions
		const allTx = [
			'2025-08-19: Paid ₹100',
			'2025-08-17: Paid ₹100',
			'2025-08-15: Paid ₹100',
			'2025-08-12: Paid ₹100',
			'2025-08-10: Paid ₹100',
			'2025-08-08: Paid ₹100'
		];
		const allTransactionList = document.getElementById('allTransactionList');
		if (allTransactionList) {
			allTransactionList.innerHTML = '';
			allTx.forEach(tx => {
				const li = document.createElement('li');
				li.className = 'list-group-item';
				li.textContent = tx;
				allTransactionList.appendChild(li);
			});
		}
	}, 500);

	// Simulate profile info
	if (document.getElementById('profileUsername')) document.getElementById('profileUsername').textContent = 'user1';
	if (document.getElementById('profileEmail')) document.getElementById('profileEmail').textContent = 'user1@email.com';
	if (document.getElementById('profileRole')) document.getElementById('profileRole').textContent = 'USER';

	// Default section
	if (dashboardSection) showSection(dashboardSection);
});
