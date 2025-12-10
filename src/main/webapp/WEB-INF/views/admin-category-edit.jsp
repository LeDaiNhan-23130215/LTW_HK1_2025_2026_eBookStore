<%@ page import="models.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa danh mục</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin-category.css" />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <!-- Bootstrap 5 -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">

    <link rel="icon" type="image/png"
          href="${pageContext.request.contextPath}/assets/img/ebook-logo2.png" />
</head>

<body>

<%
    Category c = (Category) request.getAttribute("category");
    if (c == null) {
%>
<h2 style="color: red; text-align: center; margin-top: 30px;">
    Không tìm thấy danh mục!
</h2>
<%
        return;
    }
%>

<!-- Sidebar -->
<aside class="sidebar">
    <div class="sidebar-logo">
        <h2>Ebook Admin</h2>
    </div>

    <nav class="sidebar-nav">
        <a href="${pageContext.request.contextPath}/admin-dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin-ebook">Ebook</a>
        <a href="${pageContext.request.contextPath}/admin-category" class="active">Danh mục</a>
        <a href="${pageContext.request.contextPath}/admin-user">Người dùng</a>
        <a href="${pageContext.request.contextPath}/admin-payment">Thanh toán</a>
        <a href="${pageContext.request.contextPath}/admin-banner">Banner</a>
        <a href="${pageContext.request.contextPath}/admin-news">Tin tức</a>
        <a href="${pageContext.request.contextPath}/admin-review">Review</a>
        <a href="${pageContext.request.contextPath}/admin-feedback">Feedback</a>
        <hr>
        <a href="${pageContext.request.contextPath}/admin-login" class="logout">Đăng xuất</a>
    </nav>
</aside>

<!-- Main Content -->
<div class="main-content">

    <header class="topbar">
        <div class="topbar-title">Chỉnh sửa danh mục</div>
        <button id="toggle-theme">🌙 Dark Mode</button>
    </header>

    <section class="dashboard">

        <div class="add-form">
            <h2 class="toggle-title">
                <i class="fa-solid fa-pen-to-square"></i> Sửa danh mục
            </h2>

            <form action="${pageContext.request.contextPath}/admin-category" method="post">

                <input type="hidden" name="id" value="<%= c.getId() %>">
                <input type="hidden" name="action" value="update">

                <div class="form-row">
                    <label for="catName">Tên danh mục:</label>
                    <input type="text" id="catName" name="categoryName"
                           value="<%= c.getName() %>"
                           required>
                </div>

                <div class="form-row">
                    <label for="catDesc">Mô tả:</label>
                    <textarea id="catDesc" name="description" rows="3"><%= c.getDescription() %></textarea>
                </div>

                <button type="submit" class="btn-addCategory" style="background: #28a745;">
                    Lưu thay đổi
                </button>

                <a href="${pageContext.request.contextPath}/admin-category" class="btn btn-secondary" style="margin-left: 10px;">
                    Quay lại
                </a>

            </form>
        </div>

    </section>
</div>

<script src="assets/js/admin-darkmode.js"></script>
<script src="assets/js/showForm.js"></script>
</body>
</html>
