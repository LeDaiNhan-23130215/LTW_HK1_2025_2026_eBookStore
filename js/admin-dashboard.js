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