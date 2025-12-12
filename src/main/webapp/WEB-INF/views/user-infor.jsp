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
  <% String email = (String) session.getAttribute("email"); %>
    <div class="container">
      <div class="box-left">
        <div class="subTitle">
          <h5>TRANG TÀI KHOẢN</h5>
          <p>
            <b>Xin chào, </b>
            <b style="color: hsl(0, 100%, 60%); font-weight: 550"
              ><%=userName%></b
            >
            !
          </p>
        </div>
        <ul class="option-list">
          <li class="selected"><a href="#">Thông tin tài khoản</a></li>
          <li><a href="your-order">Đơn hàng của bạn</a></li>
          <li><a href="book-shelf">Tủ sách của bạn</a></li>
          <li><a href="wishlist">Danh mục yêu thích</a></li>
          <li><a href="change-password">Đổi mật khẩu</a></li>
        </ul>
      </div>
      <div class="box-right">
        <div class="subTitle">
          <h5>THÔNG TIN TÀI KHOẢN</h5>
        </div>
        <p><b>Họ tên: </b><%=userName%></p>
        <p><b>Email: </b><%=email%></p>
      </div>
    </div>

  <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
    <script src="assets/js/component.js"></script>
    <script src="assets/js/user-infor.js" defer></script>
  </body>
</html>
