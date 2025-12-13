<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Danh mục yêu thích</title>
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
          <li><a href="your-order">Đơn hàng của bạn</a></li>
          <li><a href="book-shelf">Tủ sách của bạn</a></li>
          <li class="selected"><a href="#">Danh mục yêu thích</a></li>
          <li><a href="change-password">Đổi mật khẩu</a></li>
        </ul>
      </div>
      <div class="box-right">
        <div class="subTitle">
            <h5>Danh mục yêu thích của bạn</h5>
            <div class="wishlist-container" id="wishListContainer">
                
                <div class="wishlist-item">
                    <img src="https://tse1.mm.bing.net/th/id/OIP.fxy5-bUKrgCVIKa5hGTbZgHaG1?rs=1&pid=ImgDetMain&o=7&rm=3" alt="MachineLearning">
                    <div class="infor">
                        <h3>Machine Learning Yearning</h3>
                        <p>Tác giả: Andrew Ng</p>
                        <p><b>250.000VND</b></p>
                    </div>
                    <div class="actions">
                        <button class="add-cart" type="button">
                            <i class="fa-solid fa-cart-shopping"></i> Thêm vào giỏ
                        </button>
                        <button class="remove" type="button">
                           <i class="fa-solid fa-trash"></i> Xóa
                        </button>
                    </div>
                </div>

                <div class="wishlist-item">
                    <img src="https://ebookvie.com/wp-content/uploads/2024/12/Thay-Me-Cha-Ganh-Vac-Son-Ha.jpg" alt="Thay-mẹ-cha-gánh-vác-sơn-hà">
                    <div class="infor">
                        <h3>Thay mẹ cha gánh vác sơn hà</h3>
                        <p>Tác giả: Đoàn Công Lê Duy</p>
                        <p><b>40.000VND</b></p>
                    </div>
                    <div class="actions">
                        <button class="add-cart" type="button">
                           <i class="fa-solid fa-cart-shopping"></i> Thêm vào giỏ
                        </button>
                        <button class="remove" type="button">
                           <i class="fa-solid fa-trash"></i> Xóa
                        </button>
                    </div>
                </div>

                <div class="wishlist-item">
                    <img src="https://cdn1.fahasa.com/media/catalog/product/8/9/8935095620654.jpg" alt="Các-món-hè-Bánh-Truyền-Thống">
                    <div class="infor">
                        <h3>Các món hè & Bánh Truyền Thống</h3>
                        <p>Tác giả: Kim Phượng</p>
                        <p><b>25.000VND</b></p>
                    </div>
                    <div class="actions">
                        <button class="add-cart" type="button">
                           <i class="fa-solid fa-cart-shopping"></i> Thêm vào giỏ
                        </button>
                        <button class="remove" type="button">
                           <i class="fa-solid fa-trash"></i> Xóa
                        </button>
                    </div>
                </div>
            </div>
        </div>
      </div>
    </div>

  <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
    <script src="assets/js/component.js"></script>
    <script src="assets/js/user-infor.js" defer></script>
  </body>
</html>
