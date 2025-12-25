<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vn">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookDetail</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
    <link rel="stylesheet" href="assets/css/home.css" />
    <link rel="stylesheet" href="assets/css/bookdetail.css">
    <link rel="stylesheet" href="assets/css/bookdetail.js">
  </head>
  <body>
    <header id="siteHeader">
      <div class="headerContainer">
        <div class="topBar">
          <div class="leftContainer">
            <p id="welcomeMessage">Chào mừng bạn đến với EBookStore</p>
          </div>
          <div class="rightContainer">
            <a href="user-infor.html">Nguyễn Văn A</a>
            <a href="home-notLogin.html">Đăng xuất</a>
            <p>
              Hotline:
              <span class="phoneNumber"
                ><i class="fa-solid fa-phone phoneIcon"></i> 0354.30.09.05</span
              >
            </p>
          </div>
        </div>

        <div class="headerMenu">
          <div class="logo">
            <a href="${pageContext.request.contextPath}/home"
              ><img
                src="assets/img/ebook-logo2.png"
                alt="EBookStore Logo"
                class="logoImg"
            /></a>
            <p>EBookStore</p>
          </div>
          <div class="categorymenu">
            <a href="category.html"><i class="fa-solid fa-bars"></i> Danh mục</a>
          </div>

          <div class="searchBox">
            <input
              type="text"
              placeholder="Tìm sách của bạn ở đây"
              id="searchBox"
            />
            <button><i class="fa fa-search"></i></button>
          </div>

          <div class="after-searchbox">
            <div class="notification-button">
              <a><i class="fa-regular fa-bell"></i></a>
              <span class="notification-badge">3</span>
            </div>

            <div class="order">
              <a href="cart.html"
                ><button>
                  <i class="fa-solid fa-cart-shopping"></i> Giỏ hàng
                </button></a
              >
              <span class="notification-badge">0</span>
            </div>
          </div>
        </div>
      </div>
    </header>

<!-- Phần thẻ của trang chi tiết sách -->
 <main class="book-detail-page">
  <div class="container">
    <div class="book-detail-layout">
      <div class="main-content">
        <section class="product-summary">
          <div class="product-gallery">
            <div class="main-image-container">
              <img
                src="assets/img/10439-lam-ra-lam-choi-ra-choi-1.jpg"
                alt="Deep Work"
                class="main-image"
                id="mainBookImage"
              />
            </div>
            <div class="thumbnail-list">
              <img
                src="assets/img/10439-lam-ra-lam-choi-ra-choi-1.jpg"
                alt="Thumbnail 1"
                class="thumbnail-item active"
                onclick="changeMainImage(this.src)"
              />
              <img
                src="assets/img/10439-lam-ra-lam-choi-ra-choi-1.jpg"
                alt="Thumbnail 2"
                class="thumbnail-item"
                onclick="changeMainImage(this.src)"
              />
              <img
                src="assets/img/10439-lam-ra-lam-choi-ra-choi-1.jpg"
                alt="Thumbnail 3"
                class="thumbnail-item"
                onclick="changeMainImage(this.src)"
              />
            </div>
          </div>

          <div class="product-info">
            <h1 class="product-title">Deep Work - Carl Newport</h1>
            <div class="product-meta">
              <span>Tác giả: <a href="#">Carl Newport</a></span>
              <span>Loại: <a href="#">PDF</a></span>
              <span>Mã sản phẩm: 8934974190554</span>
            </div>

            <div class="price-box">
              <span class="current-price">85.500 VNĐ</span>
            </div>

            

            <button class="add-to-cart-btn"><a href="assets/pages/cart.html">THÊM VÀO GIỎ</a></button>
            <button class="add-to-cart-btn"><a href="assets/pages/wishlist.html">THÊM VÀO DANH SÁCH YÊU THÍCH</a></button>
            <button class="add-to-cart-btn"><a href="assets/pages/readbook.html">ĐỌC THỬ</a></button>
            <button class="add-to-cart-btn"><a href="assets/pages/review.html">XEM ĐÁNH GIÁ</a></button>

            
          </div>
        </section>

        <section class="product-description-tabs">
          <div class="tab-headers">
            <button class="tab-link active" data-tab="tab-1">
              Mô tả sản phẩm
            </button>
            <button class="tab-link" data-tab="tab-2">
              Hướng dẫn mua hàng
            </button>
          </div>

          <div id="tab-1" class="tab-content active">
            <h3>Mô tả sản phẩm</h3>
            <h4>Deep Work - Carl Newport</h4>
            <p>
Cal Newport lập luận rằng trong thời đại ngập tràn thông tin và xao nhãng, khả năng “làm việc sâu” – 
tập trung hoàn toàn vào một nhiệm vụ khó – là kỹ năng quý giá nhất. Ông đưa ra các chiến lược cụ thể để 
rèn luyện sự tập trung, loại bỏ thói quen làm việc nông, và đạt hiệu suất vượt trội. Cuốn sách kết hợp nghiên 
cứu khoa học, ví dụ thực tế và lời khuyên thực hành, giúp người đọc làm việc thông minh hơn, sáng tạo hơn và đạt thành tựu lớn mà vẫn duy trì cân bằng cuộc sống.
            </p>
          </div>

          <div id="tab-2" class="tab-content">
            <h3>Hướng dẫn mua hàng</h3>
            <p>
              <strong>Bước 1:</strong> Chọn số lượng sản phẩm và nhấn nút
              "THÊM VÀO GIỎ".
            </p>
            <p>
              <strong>Bước 2:</strong> Đi đến trang Giỏ hàng (icon ở góc
              trên bên phải) để kiểm tra lại sản phẩm.
            </p>
            <p>
              <strong>Bước 3:</strong> Nhấn "Tiến hành thanh toán", điền
              đầy đủ thông tin và chọn phương thức thanh toán.
            </p>
            <p>
              <strong>Bước 4:</strong> Xác nhận đơn hàng và chờ EBookStore
              giao sách đến tận tay bạn!
            </p>
          </div>
        </section>
      </div>

      <aside class="sidebar-detail">
        <div class="sidebar-widget">
          <h3>Có thể bạn đang tìm</h3>
          <ul class="related-products-list">
            <li class="related-product-item">
              <a href="#"
                ><img
                  src="https://m.media-amazon.com/images/I/51E2055ZGUL._SY522_.jpg"
                  alt="The Pragmatic Programmer — Andrew Hunt & David Thomas"
              /></a>
              <div class="product-details">
                <a href="#" class="related-title"
                  >The Pragmatic Programmer — Andrew Hunt & David Thomas</a
                >
                <div class="related-price">
                  <span class="current-price">495.000 VNĐ</span>
                </div>
              </div>
            </li>
            <li class="related-product-item">
              <a href="#"
                ><img
                  src="https://m.media-amazon.com/images/I/51JYkEpbhzL.jpg"
                  alt="Design Patterns: Elements of Reusable Object-Oriented Software — Gamma et al."
              /></a>
              <div class="product-details">
                <a href="#" class="related-title"
                  >Design Patterns: Elements of Reusable Object-Oriented Software — Gamma et al</a
                >
                <div class="related-price">
                  <span class="current-price">211.500 VNĐ</span>
            
                </div>
              </div>
            </li>
            <li class="related-product-item">
              <a href="#"
                ><img
                  src="https://m.media-amazon.com/images/I/814nAGmpAGL._AC_UF1000%2C1000_QL80_.jpg"
                  alt="Continuous Delivery — Jez Humble & David Farley"
              /></a>
              <div class="product-details">
                <a href="#" class="related-title"
                  >Continuous Delivery — Jez Humble & David Farley</a
                >
                <div class="related-price">
                  <span class="current-price">175.500 VNĐ</span>
             
                </div>
              </div>
            </li>
            <li class="related-product-item">
              <a href="#"
                ><img
                  src="https://m.media-amazon.com/images/I/71CjT0N23ML.jpg"
                  alt="Code Complete — Steve McConnell"
              /></a>
              <div class="product-details">
                <a href="#" class="related-title"
                  >Code Complete — Steve McConnell</a
                >
                <div class="related-price">
                  <span class="current-price">342.000 VNĐ</span>
               
                </div>
              </div>
            </li>
          </ul>
        </div>
      </aside>
    </div>
  </div>

    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

    <script>
      const ctx = "${pageContext.request.contextPath}";
    </script>
    <script src="assets/js/bookdetail.js"></script>
    <script src="assets/js/home.js"></script>

  </body>
</html>
