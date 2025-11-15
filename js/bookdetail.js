// Chờ tất cả nội dung trang tải xong
document.addEventListener("DOMContentLoaded", function () {
  // --- Xử lý chuyển ảnh thumbnail ---
  const mainImage = document.getElementById("mainBookImage");
  const thumbnails = document.querySelectorAll(".thumbnail-item");

  // Gán sự kiện click cho từng ảnh thumbnail
  thumbnails.forEach((thumb) => {
    thumb.addEventListener("click", function () {
      // Bỏ class 'active' khỏi tất cả
      thumbnails.forEach((item) => item.classList.remove("active"));
      // Thêm class 'active' cho ảnh được click
      this.classList.add("active");
      // Thay ảnh chính
      mainImage.src = this.src;
    });
  });

  // --- Xử lý tăng giảm số lượng ---
  const decreaseBtn = document.getElementById("decreaseQty");
  const increaseBtn = document.getElementById("increaseQty");
  const quantityInput = document.getElementById("quantity");

  decreaseBtn.addEventListener("click", function () {
    let currentValue = parseInt(quantityInput.value);
    if (currentValue > 1) {
      quantityInput.value = currentValue - 1;
    }
  });

  increaseBtn.addEventListener("click", function () {
    let currentValue = parseInt(quantityInput.value);
    quantityInput.value = currentValue + 1;
  });

  // --- Xử lý chuyển Tab ---
  const tabLinks = document.querySelectorAll(".tab-link");
  const tabContents = document.querySelectorAll(".tab-content");

  tabLinks.forEach((link) => {
    link.addEventListener("click", function () {
      const tabId = this.getAttribute("data-tab"); // Lấy ID của tab (ví dụ: "tab-1")

      // Bỏ 'active' khỏi tất cả link và content
      tabLinks.forEach((item) => item.classList.remove("active"));
      tabContents.forEach((item) => item.classList.remove("active"));

      // Thêm 'active' cho link và content tương ứng
      this.classList.add("active");
      document.getElementById(tabId).classList.add("active");
    });
  });
});

// Ghi đè hàm global (nếu bạn dùng onclick="" trong HTML)
// Cách này không được khuyến khích bằng cách dùng addEventListener ở trên
// nhưng tôi vẫn để đây phòng trường hợp bạn dùng cách copy/paste HTML cũ
function changeMainImage(newSrc) {
  document.getElementById("mainBookImage").src = newSrc;
  // (Nên cập nhật thêm class 'active' cho thumbnail như ở trên)
}