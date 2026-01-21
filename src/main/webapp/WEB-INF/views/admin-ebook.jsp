<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Ebook Manager</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin-ebook.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin-form.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/ebook-logo2.png"/>

    <!-- Bootstrap + DataTable -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">

    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#ebookTable').DataTable({
                pageLength: 5,
                lengthMenu: [5, 10, 20, 50],
                ordering: true,
                searching: true,
                language: {
                    lengthMenu: "Hiển thị _MENU_ dòng",
                    search: "Tìm kiếm:",
                    info: "Trang _PAGE_ / _PAGES_",
                    paginate: {
                        first: "Đầu",
                        last: "Cuối",
                        next: "Tiếp",
                        previous: "Trước"
                    },
                    zeroRecords: "Không tìm thấy dữ liệu"
                }
            });
        });
    </script>
</head>

<body>

<!-- Sidebar -->
<aside class="sidebar">
    <div class="sidebar-logo"><h2>Ebook Admin</h2></div>
    <nav class="sidebar-nav">
        <a href="${pageContext.request.contextPath}/admin-dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin-ebook" class="active">Ebook</a>
        <a href="${pageContext.request.contextPath}/admin-author">Tác giả</a>
        <a href="${pageContext.request.contextPath}/admin-category">Danh mục</a>
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

<!-- Main -->
<div class="main-content">

    <header class="topbar">
        <div class="topbar-title">Quản lý eBook</div>
        <button id="toggle-theme">🌙 Dark Mode</button>
    </header>

    <!-- Form thêm ebook -->
    <section class="dashboard">
        <div class="add-form">
            <h2><i class="fa-solid fa-plus"></i> Thêm eBook</h2>

            <form action="${pageContext.request.contextPath}/admin-ebook"
                  method="post">

                <input type="hidden" name="action" value="add"/>

                <div class="form-row">
                    <label>Tên sách:</label>
                    <input type="text" name="title" required>
                </div>

                <div class="form-row">
                    <label>Tác giả:</label>
                    <select name="authorId" required>
                        <option value="">-- Chọn tác giả --</option>
                        <c:forEach var="a" items="${authors}">
                            <option value="${a.id}">${a.authorName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-row">
                    <label>Thể loại:</label>
                    <select name="categoryId" required>
                        <option value="">-- Chọn thể loại --</option>
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}">${c.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-row">
                    <label>Giá:</label>
                    <input type="number" name="price" required>
                </div>

                <div class="form-row">
                    <label>Ảnh bìa (URL):</label>
                    <input type="text" name="coverUrl">
                </div>

                <div class="form-row">
                    <label>File ebook:</label>
                    <input type="text" name="filePath">
                </div>

                <div class="form-row">
                    <label>Mô tả:</label>
                    <textarea name="description"></textarea>
                </div>

                <button type="submit" class="btn-addEbook">
                    Thêm sách
                </button>
            </form>
        </div>
    </section>

    <!-- Table -->
    <section class="table-section">
        <table id="ebookTable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên sách</th>
                <th>Tác giả</th>
                <th>Thể loại</th>
                <th>Giá</th>
                <th>Thao tác</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="e" items="${ebooks}">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.title}</td>
                    <td>${authorMap[e.authorID]}</td>
                    <td>${categoryMap[e.categoryID]}</td>
                    <td>${e.price}</td>
                    <td id="btn-place">
                        <a class="btn-Edit"
                           href="${pageContext.request.contextPath}/admin-ebook?action=edit&id=${e.id}">
                            <i class="fa-solid fa-pen-to-square"></i> Sửa
                        </a>

                        <a class="btn-Del"
                           onclick="return confirm('Bạn có chắc muốn xóa ebook này?')"
                           href="${pageContext.request.contextPath}/admin-ebook?action=delete&id=${e.id}">
                            <i class="fa-solid fa-trash"></i> Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

</div>

<script src="${pageContext.request.contextPath}/assets/js/admin-darkmode.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/showForm.js"></script>
</body>
</html>