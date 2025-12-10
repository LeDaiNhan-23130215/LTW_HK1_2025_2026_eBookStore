<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
    <link rel="stylesheet" href="assets/css/cart.css" />
</head>
<body>
    <!-- #Header -->
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

    <div class="container">
        <div class="inner-container">
            <div class="cart-container">
                <form action="">
                    <div class="cart-header">
                        <div class="header">Thông tin sản phẩm</div>
                        <div class="header">Đơn giá</div>
                        <div class="header">Thành tiền</div>
                    </div>
                    <div class="cart-inner">
                        <div class="cart-row">
                            <!-- Cột 1: Sản phẩm -->
                            <div class="cart-product" data-line="1">
                                <a href="#" class="cart-img" title="Deep Work">
                                <img src="https://tse2.mm.bing.net/th/id/OIP.IUVt53fcwXP23-Snmv6SfAHaG1?pid=Api&P=0&h=180" alt="Deep Work">
                                </a>
                                <div class="cart-infor">
                                <div class="cart-name">
                                    <a href="#" class="product_name" title="Deep Work">Deep Work</a>
                                    <a class="remove-item-cart" data-line="1">Xóa</a>
                                </div>
                                </div>
                            </div>

                            <!-- Cột 2: Đơn giá -->
                            <div class="cart-price">
                                <span>280.000 VND</span>
                            </div>

                            <!-- Cột 3: Thành tiền -->
                            <div class="cart-price">
                                <span>280.000 VND</span>
                            </div>
                        </div>
                    </div>
                    <div class="cart-footer">
                        <div class="footer-row">
                            <div class="footer-container">
                                <div></div>
                                <div class="cart-btn">
                                    <a href="contact-information.html"><button type="button">Thanh toán</button></a>
                                    <div class="term">
                                        Bằng việc mua hàng, bạn đồng ý với  
                                        <br>
                                        <a href="#">Điều khoản & Điều kiện EBOOK</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- #Footer -->
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    
    <script src="assets/js/component.js"></script>
</body>
</html>