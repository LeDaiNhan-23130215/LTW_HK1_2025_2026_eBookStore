<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link rel="stylesheet" href="assets/css/sign-up.css" />
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
            <a href="home-Login.html"
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

    <form action="${pageContext.request.contextPath}/sign-up" method="post">
      <div class="container">
        <p class="header">Đăng ký</p>
          <c:if test="${not empty error_msg}">
              <p style="color:red; margin-bottom: 10px">${error_msg}</p>
          </c:if>

          <c:if test="${not empty success_msg}">
              <p style="color:green">${success_msg}</p>
              <script>
                  setTimeout(function() {
                      window.location.href = "${pageContext.request.contextPath}/home";
                  }, 2000);
              </script>
          </c:if>
        <div class="input">
          <div class="top-input">
            <div class="input-div">
              <input
                type="text"
                name="fname"
                id="fname"
                placeholder="Tên người dùng"
                value="${param.fname}"
                required
              />
            </div>
          </div>
          <div class="mid-input">
            <div class="input-div">
              <input
                type="email"
                name="userAndEmail"
                id="userAndEmail"
                placeholder="Email"
                value="${param.userAndEmail}"
                required
              />
            </div>
            <div class="input-div">
              <input
                type="tel"
                name="phoneNumber"
                id="phoneNumber"
                placeholder="Số điện thoại"
                value="${param.phoneNumber}"
                pattern="[0-9]{10,11}"
                required
              />
            </div>
          </div>
          <div class="bottom-input">
            <div class="input-div">
              <input
                type="password"
                name="password"
                id="password"
                placeholder="Mật khẩu"
                autocomplete="new-password"
                required
              />
            </div>
            <div class="input-div">
              <input
                type="password"
                name="confirmPassword"
                id="confirmPassword"
                placeholder="Nhập lại mật khẩu"
                autocomplete="new-password"
                required
              />
            </div>
          </div>
          <button type="submit" class="signUp-btn">Đăng ký</button>
        </div>
        <p class="anotherOption">Hoặc đăng nhập bằng</p>
        <div class="logoContainer">
          <ul class="logoList">
            <li><a href="#"></a><i class="fa-brands fa-facebook-f"></i></li>
            <li><a href="#"></a><i class="fa-brands fa-google"></i></li>
          </ul>
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
  </body>
</html>
