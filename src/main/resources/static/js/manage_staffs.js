
let staffs = [];

async function fetchStaffs() {
	try {
		const res = await fetch('/api/user/all');
		if (res.ok) {
			const allUsers = await res.json();
			staffs = allUsers.filter(u => u.role === 'STAFF');
			renderStaffs();
		} else {
			alert('Failed to fetch staff from server.');
		}
	} catch (err) {
		alert('Server error while fetching staff.');
	}
}

function renderStaffs() {
	const tbody = document.querySelector('#staffsTable tbody');
	tbody.innerHTML = '';
	staffs.forEach(staff => {
		const tr = document.createElement('tr');
		tr.innerHTML = `
			<td data-label="ID">${staff.id}</td>
			<td data-label="Username">${staff.username}</td>
			<td data-label="Role">${staff.role}</td>
			<td data-label="Actions">
				<button class="btn btn-sm btn-primary me-2 editBtn" data-id="${staff.id}">Edit</button>
				<button class="btn btn-sm btn-danger deleteBtn" data-id="${staff.id}">Delete</button>
			</td>
		`;
		tbody.appendChild(tr);
	});
}

// Open modal for add
document.getElementById('addStaffBtn').addEventListener('click', function() {
	document.getElementById('staffForm').reset();
	document.getElementById('staffId').value = '';
	document.getElementById('staffModalLabel').textContent = 'Add User';
});

// Save staff (add or edit)
document.getElementById('staffForm').addEventListener('submit', async function(e) {
	e.preventDefault();
	const id = document.getElementById('staffId').value;
	const username = document.getElementById('modalStaffUsername').value;
	const password = document.getElementById('modalStaffPassword').value;
	const role = document.getElementById('modalStaffRole').value;
	if (id) {
		// Edit staff
		try {
			const res = await fetch(`/api/user/${id}`, {
				method: 'PUT',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ username, password, role })
			});
			if (res.ok) {
				await fetchStaffs();
				bootstrap.Modal.getOrCreateInstance(document.getElementById('staffModal')).hide();
			} else if (res.status === 409) {
				alert('Username already exists!');
			} else {
				alert('Error updating staff!');
			}
		} catch (err) {
			alert('Server error!');
		}
	} else {
		// Add staff
		try {
			const res = await fetch('/api/user/register', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ username, password, role })
			});
			if (res.ok) {
				await fetchStaffs();
				bootstrap.Modal.getOrCreateInstance(document.getElementById('staffModal')).hide();
			} else if (res.status === 409) {
				alert('Username already exists!');
			} else {
				alert('Error adding staff!');
			}
		} catch (err) {
			alert('Server error!');
		}
	}
});

// Edit and Delete button handlers
document.querySelector('#staffsTable tbody').addEventListener('click', async function(e) {
	if (e.target.classList.contains('editBtn')) {
		const id = e.target.getAttribute('data-id');
		const staff = staffs.find(u => u.id == id);
		if (staff) {
			document.getElementById('staffId').value = staff.id;
			document.getElementById('modalStaffUsername').value = staff.username;
			document.getElementById('modalStaffPassword').value = '';
			document.getElementById('modalStaffRole').value = staff.role;
			document.getElementById('staffModalLabel').textContent = 'Edit Staff';
			bootstrap.Modal.getOrCreateInstance(document.getElementById('staffModal')).show();
		}
	}
	if (e.target.classList.contains('deleteBtn')) {
		const id = e.target.getAttribute('data-id');
		if (confirm('Are you sure you want to delete this staff?')) {
			try {
				const res = await fetch(`/api/user/${id}`, { method: 'DELETE' });
				if (res.ok) {
					await fetchStaffs();
				} else {
					alert('Error deleting staff!');
				}
			} catch (err) {
				alert('Server error!');
			}
		}
	}
});

// Initial fetch
fetchStaffs();
