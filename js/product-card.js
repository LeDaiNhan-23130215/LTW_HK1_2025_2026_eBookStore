document.querySelectorAll('.add-to-cart-btn').forEach(btn => {
  btn.addEventListener('click', (event) => {
    event.stopPropagation();

    // Thêm class animation
    btn.classList.add('adding');

    // Sau 700ms mới chuyển trang để nhìn thấy hiệu ứng
    setTimeout(() => {
      window.location.href = 'cart.html';
    }, 700);
  });
});

const productCards = document.querySelectorAll('.product-card');
productCards.forEach(card => {
  card.addEventListener('click', () => {
    window.location.href = "../pages/bookdetail.html"
  });
});
