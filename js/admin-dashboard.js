document.querySelectorAll('.card').forEach(card => {
    card.addEventListener('click', () => {
        if (card.id === 'totalEbooks') {
            window.location.href = '../pages/admin-ebook.html'
        }
        if (card.id === 'totalUsers') {
            window.location.href = '../pages/admin-user.html'
        }
        if (card.id === 'totalOrders' || card.id === 'totalRevenue'){
            window.location.href = '../pages/admin-payment.html'
        }
    });
});

document.addEventListener("DOMContentLoaded", () => {

  const ctx = document.getElementById("revenueChart");

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: ["Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10"],
      datasets: [{
        label: "Doanh thu (VND)",
        data: [1200000, 1500000, 2100000, 1800000, 2600000, 3000000],
        borderWidth: 3,
        borderColor: "rgba(75, 132, 255, 1)",
        backgroundColor: "rgba(75, 132, 255, 0.3)",
        tension: 0.3,
        pointRadius: 4,
        fill: true
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: { beginAtZero: false }
      }
    }
  });

});