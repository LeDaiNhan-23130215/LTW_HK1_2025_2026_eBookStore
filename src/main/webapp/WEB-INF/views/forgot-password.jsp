<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/forgot-password.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
  </head>
  <body>
    <header id="siteHeader">
      <div class="headerContainer">
        <div class="topBar">
          <div class="leftContainer">
            <p id="welcomeMessage">Chào mừng bạn đến với EBookStore</p>
          </div>
          <div class="rightContainer">
            <a href="login.html">Đăng nhập</a>
            <a href="sign-up.html">Đăng ký</a>
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
            <a href="home-notLogin.html"
              ><img
                src="assets/img/ebook-logo2.png"
                alt="EBookStore Logo"
                class="logoImg"
            /></a>
            <p>EBookStore</p>
          </div>
          <div class="categorymenu">
            <a href="category.html"
              ><i class="fa-solid fa-bars"></i> Danh mục</a
            >
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

    <form action="">
      <div class="container">
            <p class="header">Quên mật khẩu</p>
            <div class="input">
                <div class="email-input">
                    <input type="text" name="userAndEmail" id="userAndEmail" placeholder="Vui lòng nhập email của bạn"/>
                    <button type="button" class="code-btn">Gửi mã</button>
                </div>
                <div class="code-input">
                    <input type="text" name="confirmCode" id="confirmCode" placeholder="Nhập mã xác nhận tại đây"/>
                    <button type="button" class="code-btn">Xác nhận</button>
                </div>
                <div class="password-input">
                    <input type="password" name="newPassword" id="newPassword" placeholder="Mật khẩu mới của bạn"/>
                    <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Xác nhận mật khẩu mới"/>
                    <button type="button" class="confirm-btn">Đổi mật khẩu</button>
                </div>
            </div>
      </div>
    </form>

    <footer id="siteFooter">
      <div class="topFooter">
        <div class="left-container">
          <div class="informationEmailBox">
            <input
              type="text"
              placeholder="Nhập email để nhận thông tin mới nhất!"
              id="email-input"
            />
            <a>ĐĂNG KÝ</a>
          </div>
        </div>

        <div class="right-container">
          <p>Kết nối với chúng tôi:</p>
          <img src="assets/img/zalo-logo.png" alt="zalo" class="zalo-logo" />
          <img
            src="assets/img/facebook-logo.png"
            alt="facebook"
            class="facebook-logo"
          />
        </div>
      </div>

      <div class="footer-container">
        <div class="left">
          <div class="logo">
            <a href="home-Login.html"
              ><img
                src="assets/img/ebook-logo2.png"
                alt="EBookStore Logo"
                class="logoImg"
            /></a>
            <p>EBookStore</p>
          </div>
          <h3>Công ty EBookStore</h3>
          <p>
            Địa chỉ: Khu phố 33, Phường Linh Xuân, TP. Hồ Chí Minh, Việt Nam
          </p>
          <p>
            <span>EBookStore</span> là mô hình nhà sách dành cho thế hệ trẻ,
            tích hợp đa dạng sản phẩm các loại sách
          </p>
          <a href="assets/pages/address.html" class="address">
            <i class="fa-solid fa-location-dot"></i> Khu phố 33, Phường Linh
            Xuân, TP. Hồ Chí Minh, Việt Nam</a
          >

          <p><i class="fa-solid fa-phone phoneIcon"></i> 0354.30.09.05</p>
          <p><i class="fa-solid fa-envelope"></i> ebookstore@gmail.com</p>
        </div>

        <div class="cs-hd-dm">
          <div class="option">
            <h3>CHÍNH SÁCH</h3>
            <ul>
              <li><a href="">Chính Sách Bảo Mật Thông Tin Cá Nhân</a></li>
              <li><a href="">Chính Sách Bảo Mật Thanh Toán</a></li>
              <li><a href="">Điều Khoản sử dụng</a></li>
            </ul>
          </div>

          <div class="option">
            <h3>HƯỚNG DẪN</h3>
            <ul>
              <li><a href="">Hướng dẫn mua hàng</a></li>
              <li><a href="">Phương thức thanh toán</a></li>
            </ul>
          </div>

          <div class="option">
            <h3>DANH MỤC</h3>
            <ul>
              <li><a href="assets/pages/home-Login.html">Trang chủ</a></li>
              <li><a href="assets/pages/about.html">Giới Thiệu</a></li>
              <li><a href="assets/pages/news.html">Tin tức</a></li>
              <li><a href="assets/pages/contact.html">Liên hệ</a></li>
              <li><a href="assets/pages/faq.html">FAQ</a></li>
            </ul>
          </div>

          <div class="kn-tt">
            <h3>GÓP Ý KHIẾU NẠI</h3>
            <a href="#">0354.30.09.05</a>
            <div class="footer-feedback">
              <p>Gửi feedback của bạn tại đây:</p>
              <a href="assets/pages/feedback.html" class="feedback-button">
                Feedback</a
              >
            </div>

            <p>Tất cả các ngoài trong tuần</p>
            <p>(Trừ thứ 7 - CN, các ngày lễ)</p>
            <h3>THANH TOÁN</h3>
            <div class="payment-grid">
              <img src="assets/img/momo-logo.png" alt="momo" class="payment-logo" />
              <img src="assets/img/mb-logo.png" alt="mbbank" class="payment-logo" />
              <img
                src="assets/img/zalo-pay.png"
                alt="zalopay"
                class="payment-logo"
              />
              <img src="assets/img/vnpay.png" alt="vnpay" class="payment-logo" />
            </div>
          </div>
        </div>
      </div>

      <div class="footer-bottom">
        <p>Chúc bạn tìm được eBook ưng ý!!!</p>
      </div>
    </footer>
    
    <script src="assets/js/component.js"></script>
    <script src="assets/js/forgot-password.js"></script>
  </body>
</html>
