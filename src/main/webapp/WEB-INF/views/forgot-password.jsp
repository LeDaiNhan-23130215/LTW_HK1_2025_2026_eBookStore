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
  <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

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

  <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
    
    <script src="assets/js/component.js"></script>
    <script src="assets/js/forgot-password.js"></script>
  </body>
</html>
