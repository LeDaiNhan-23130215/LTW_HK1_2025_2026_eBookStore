<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thông tin nhận hàng</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/components.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/contact-information.css"/>

    <link rel="icon" type="image/png"
          href="${pageContext.request.contextPath}/assets/img/ebook-logo2.png"/>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
</head>

<body>
<div class="checkout-container">

    <!-- LEFT -->
    <div class="checkout-left">
        <div class="image">
            <img src="${pageContext.request.contextPath}/assets/img/ebook-logo2.png" alt="logo">
        </div>
        <div class="label-img">EBookStore</div>

        <div class="label-logout">
            <p>Thông tin nhận hàng</p>
            <a href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket"></i> Đăng xuất
            </a>
        </div>

        <div class="form-container">
            <form action="${pageContext.request.contextPath}/checkout" method="post">

                <div class="input">
                    <input type="email" name="userAndEmail"
                           value="${not empty email ? email : param.email}" required>
                    <label>Email</label>
                </div>

                <div class="input">
                    <input type="text" name="fname"
                           value="${not empty userName ? userName : param.userName}" required>
                    <label>Họ và tên</label>
                </div>

                <div class="input">
                    <input type="text" name="phoneNumber"
                           value="${not empty phoneNum ? phoneNum : param.phoneNum}" required>
                    <label>Số điện thoại</label>
                </div>

                <div class="input">
                    <textarea name="note"></textarea>
                    <label>Ghi chú (tùy chọn)</label>
                </div>

                <div class="option">
                    <a href="${pageContext.request.contextPath}/cart">
                        <i class="fa-solid fa-arrow-left"></i> Giỏ hàng
                    </a>
                    <button type="submit" class="continue-btn">
                        Tiếp tục thanh toán
                    </button>
                </div>

            </form>
        </div>
    </div>

    <!-- RIGHT -->
    <div class="checkout-right">
        <div class="label-right">
            Đơn hàng (${cartItems.size()} sản phẩm)
        </div>

        <c:forEach var="item" items="${cartItems}">
            <div class="product-row">
                <div class="product-detail">
                    <img src="${item.image.imgLink}" alt="${item.image.imgName}">
                    <div class="product-name">${item.ebook.title}</div>
                </div>
                <div class="product-price">
                    <fmt:formatNumber value="${item.priceAtADD}" type="number"/> VND
                </div>
            </div>
        </c:forEach>

        <div class="price">
            <div class="sub-price-title">Tạm tính</div>
            <div class="product-price"><fmt:formatNumber value="${totalPrice}" type="number"/> VND</div>
        </div>

        <div class="total">
            <div class="total-price-title">Tổng tiền</div>
            <div class="total-price"><fmt:formatNumber value="${totalPrice}" type="number"/> VND</div>
        </div>
    </div>

</div>

<%--<script src="${pageContext.request.contextPath}/assets/js/contact-information.js"></script>--%>
</body>
</html>
