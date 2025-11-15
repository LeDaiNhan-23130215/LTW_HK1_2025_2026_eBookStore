document.querySelectorAll('.add-to-cart-btn').forEach(btn => {
  btn.addEventListener('click', (event) => {
    event.stopPropagation();
    window.location.href = 'cart.html';
  });
});

const productCards = document.querySelectorAll('.product-card');
productCards.forEach(card => {
  card.addEventListener('click', () => {
    window.location.href = "../pages/bookdetail.html"
  });
});


