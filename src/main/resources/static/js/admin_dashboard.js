// Example JS for admin dashboard
// Replace with real API calls as needed

document.addEventListener('DOMContentLoaded', function() {
    // Simulate fetching stats
    setTimeout(() => {
        document.getElementById('staffCount').textContent = 12;
        document.getElementById('userCount').textContent = 48;
        document.getElementById('tollCount').textContent = 5;

        // Simulate recent activities
        const activities = [
            'User John registered',
            'Staff Alice updated toll rates',
            'User Bob paid toll',
            'Admin reviewed reports'
        ];
        const activityList = document.getElementById('activityList');
        activityList.innerHTML = '';
        activities.forEach(act => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = act;
            activityList.appendChild(li);
        });
    }, 500);
});
