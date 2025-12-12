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
              ><%=userName%></b
            >
            !
          </p>
        </div>
        <ul class="option-list">
          <li><a href="userInformation">Thông tin tài khoản</a></li>
          <li class="selected"><a href="#">Đơn hàng của bạn</a></li>
          <li><a href="book-shelf">Tủ sách của bạn</a></li>
          <li><a href="wishlist">Danh mục yêu thích</a></li>
          <li><a href="change-password">Đổi mật khẩu</a></li>
        </ul>
      </div>
      <div class="box-right">
        <div class="subTitle">
          <h5>ĐƠN HÀNG CỦA BẠN</h5>
        </div>
        <table>
          <thead>
            <tr>
              <th>Đơn hàng</th>
              <th>Ngày</th>
              <th>Giá trị đơn hàng</th>
              <th>TT thanh toán</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="6">Không có đơn hàng nào.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

  <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

    <script src="assets/js/user-infor.js" defer></script>
    <script src="assets/js/component.js"></script>
  </body>
</html>
