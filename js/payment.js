const checkoutBtn = document.querySelector('.checkout-btn');
const qrContainer = document.querySelector('.qr-container');
const paymentRadios = document.querySelectorAll('input[name="payment"]');

// Ẩn/hiện QR khi người dùng chọn phương thức
paymentRadios.forEach(radio => {
  radio.addEventListener('change', () => {
    if (radio.value === 'qrcode') {
      qrContainer.classList.remove('hidden');
      // nhỏ delay để animation hoạt động
      setTimeout(() => qrContainer.classList.add('active'), 10);
    } else {
      qrContainer.classList.remove('active');
      setTimeout(() => qrContainer.classList.add('hidden'), 300);
    }
  });
});

function startLoading() {
  checkoutBtn.textContent = "Đang xử lý...";
  checkoutBtn.disabled = true;
}

checkoutBtn.addEventListener('click', () => {
  const selectedPayment = document.querySelector('input[name="payment"]:checked')?.value;

  if (!selectedPayment) {
    alert("Vui lòng chọn phương thức thanh toán!");
    return;
  }

  if (selectedPayment === 'momo') {
    startLoading();
    setTimeout(() => window.location.href = 'https://momo.vn', 2000);
  } 
  else if (selectedPayment === 'vnpay') {
    startLoading();
    setTimeout(() => window.location.href = 'https://vnpay.vn', 2000);
  } 
  else if (selectedPayment === 'zalopay') {
    startLoading();
    setTimeout(() => window.location.href = 'https://zalopay.vn', 2000);
  } 
  else if (selectedPayment === 'qrcode') {
    alert("Vui lòng quét mã QR để hoàn tất thanh toán.");
  }
});
