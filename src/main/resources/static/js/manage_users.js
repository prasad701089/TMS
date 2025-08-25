


let users = [];

async function fetchUsers() {
	try {
		const res = await fetch('/api/user/all');
		if (res.ok) {
			users = await res.json();
			users = users.filter(u => u.role === 'USER');
			renderUsers();
		} else {
			alert('Failed to fetch users from server.');
		}
	} catch (err) {
		alert('Server error while fetching users.');
	}
}

function renderUsers() {
	const tbody = document.querySelector('#usersTable tbody');
	tbody.innerHTML = '';
	users.forEach(user => {
		const tr = document.createElement('tr');
		tr.innerHTML = `
			<td data-label="ID">${user.id}</td>
			<td data-label="Username">${user.username}</td>
			<td data-label="Role">${user.role}</td>
			<td data-label="Actions">
				<button class="btn btn-sm btn-primary me-2 editBtn" data-id="${user.id}">Edit</button>
				<button class="btn btn-sm btn-danger deleteBtn" data-id="${user.id}">Delete</button>
			</td>
		`;
		tbody.appendChild(tr);
	});
}

// Open modal for add
document.getElementById('addUserBtn').addEventListener('click', function() {
	document.getElementById('userForm').reset();
	document.getElementById('userId').value = '';
	document.getElementById('userModalLabel').textContent = 'Add User';
});

// Save user (add or edit)
document.getElementById('userForm').addEventListener('submit', async function(e) {
	e.preventDefault();
	const id = document.getElementById('userId').value;
	const username = document.getElementById('modalUsername').value;
	const password = document.getElementById('modalPassword').value;
	const role = document.getElementById('modalRole').value;
	if (id) {
			// Edit user
			try {
				const res = await fetch(`/api/user/${id}`, {
					method: 'PUT',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({ username, password, role })
				});
				if (res.ok) {
					await fetchUsers();
					bootstrap.Modal.getOrCreateInstance(document.getElementById('userModal')).hide();
				} else if (res.status === 409) {
					alert('Username already exists!');
				} else {
					alert('Error updating user!');
				}
			} catch (err) {
				alert('Server error!');
			}
	} else {
		// Add user
		try {
			const res = await fetch('/api/user/register', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ username, password, role })
			});
			if (res.ok) {
				await fetchUsers();
				bootstrap.Modal.getOrCreateInstance(document.getElementById('userModal')).hide();
			} else if (res.status === 409) {
				alert('Username already exists!');
			} else {
				alert('Error adding user!');
			}
		} catch (err) {
			alert('Server error!');
		}
	}
});

// Edit and Delete button handlers
document.querySelector('#usersTable tbody').addEventListener('click', async function(e) {
	if (e.target.classList.contains('editBtn')) {
		const id = e.target.getAttribute('data-id');
		const user = users.find(u => u.id == id);
		if (user) {
			document.getElementById('userId').value = user.id;
			document.getElementById('modalUsername').value = user.username;
			document.getElementById('modalPassword').value = '';
			document.getElementById('modalRole').value = user.role;
			document.getElementById('userModalLabel').textContent = 'Edit User';
			bootstrap.Modal.getOrCreateInstance(document.getElementById('userModal')).show();
		}
	}
	if (e.target.classList.contains('deleteBtn')) {
		const id = e.target.getAttribute('data-id');
		if (confirm('Are you sure you want to delete this user?')) {
			try {
				const res = await fetch(`/api/user/${id}`, { method: 'DELETE' });
				if (res.ok) {
					await fetchUsers();
				} else {
					alert('Error deleting user!');
				}
			} catch (err) {
				alert('Server error!');
			}
		}
	}
});

// Initial fetch
fetchUsers();
