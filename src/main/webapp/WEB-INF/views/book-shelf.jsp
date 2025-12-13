<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Thông tin người dùng</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link rel="stylesheet" href="assets/css/user-infor.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
  </head>
  <body>
  <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
  <% String userName = (String) session.getAttribute("userName"); %>
    <div class="container">
      <div class="box-left">
        <div class="subTitle">
          <h5>TRANG TÀI KHOẢN</h5>
          <p>
            <b>Xin chào, </b>
            <b style="color: hsl(0, 100%, 60%); font-weight: 550"
              ><%=userName%>
            </b>
            !
          </p>
        </div>
        <ul class="option-list">
          <li><a href="userInformation">Thông tin tài khoản</a></li>
          <li><a href="your-order">Đơn hàng của bạn</a></li>
          <li class="selected"><a href="#">Tủ sách của bạn</a></li>
          <li><a href="wishlist">Danh mục yêu thích</a></li>
          <li><a href="change-password">Đổi mật khẩu</a></li>
        </ul>
      </div>
      <section class="box-right" id="page-content">
        <h5>TỦ SÁCH CỦA BẠN</h5>
        <p>Dưới đây là các cuốn sách bạn đã sở hữu:</p>

        <div class="bookshelf">
          <!-- Sách 1 -->
          <div class="book">
            <img
              src="https://tse2.mm.bing.net/th/id/OIP.IUVt53fcwXP23-Snmv6SfAHaG1?pid=Api&P=0&h=180"
              alt="Deep Work"
            />
            <h3>Deep Work</h3>
          </div>

          <!-- Sách 2 -->
          <div class="book">
            <img
              src="https://i.pinimg.com/originals/03/5f/87/035f871609ba334ee7bd3766ccdc3f62.jpg"
              alt="Atomic Habits"
            />
            <h3>Atomic Habits</h3>
          </div>
        </div>
      </section>
    </div>

  <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
    <script src="assets/js/component.js"></script>
    <script src="assets/js/user-infor.js" defer></script>
  </body>
</html>
