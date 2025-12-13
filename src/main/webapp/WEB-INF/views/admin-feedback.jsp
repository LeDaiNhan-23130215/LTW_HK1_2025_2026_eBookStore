<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin - Quản lý Feedback</title>
    <link rel="stylesheet" href="assets/css/admin-feedback.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/img/ebook-logo2.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <!-- Bootstrap 5 -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">

    <!-- DataTables + Bootstrap 5 theme -->
    <link rel="stylesheet"
          href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">

    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#activityTable').DataTable({
                "pageLength": 5,
                "lengthMenu": [5, 10, 20, 50],
                "ordering": true,
                "searching": true,
                "language": {
                    "lengthMenu": "Hiển thị _MENU_ dòng",
                    "search": "Tìm kiếm:",
                    "info": "Trang _PAGE_ / _PAGES_",
                    "paginate": {
                        "first": "Đầu",
                        "last": "Cuối",
                        "next": "Tiếp",
                        "previous": "Trước"
                    },
                    "zeroRecords": "Không tìm thấy dữ liệu"
                }
            });
        });
    </script>
</head>
<body>
    <aside class="sidebar">
        <div class="sidebar-logo"><h2>Ebook Admin</h2></div>
        <nav class="sidebar-nav">
            <a href="${pageContext.request.contextPath}/admin-dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin-ebook">Ebook</a>
            <a href="${pageContext.request.contextPath}/admin-category">Danh mục</a>
            <a href="${pageContext.request.contextPath}/admin-user">Người dùng</a>
            <a href="${pageContext.request.contextPath}/admin-payment">Thanh toán</a>
            <a href="${pageContext.request.contextPath}/admin-banner">Banner</a>
            <a href="${pageContext.request.contextPath}/admin-news">Tin tức</a>
            <a href="${pageContext.request.contextPath}/admin-review">Review</a>
            <a href="${pageContext.request.contextPath}/admin-feedback" class="active">Feedback</a>
            <hr>
            <a href="${pageContext.request.contextPath}/admin-login" class="logout">Đăng xuất</a>
        </nav>
    </aside>

    <div class="main-content">
        <header class="topbar">
            <div class="topbar-title">Quản lý Feedback</div>
            <button id="toggle-theme">🌙 Dark Mode</button>
        </header>

        <!-- Search Bar -->
        <div class="search-wrapper">
            <div class="search-bar">
                <input type="text" id="adminSearchInput" placeholder="Tìm kiếm...">
                <button type="button" id="adminSearchBtn">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
        </div>

        <section class="table-section">
            <table id="activityTable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên người gửi</th>
                <th>Email</th>
                <th>Nội dung</th>
                <th>Ngày gửi</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody id="feedbackTableBody">
            <tr>
                <td>1</td>
                <td>Trần Minh</td>
                <td>minhtran@example.com</td>
                <td>Trang web rất tiện lợi, ...</td>
                <td>2025-10-20</td>
                <td>Chưa đọc</td>
                <td>
                <button class="btn-View"><i class="fa-solid fa-eye"></i> Xem</button>
                <button class="btn-Del"><i class="fa-solid fa-trash"></i> Xóa</button>
                </td>
            </tr>
            </tbody>
        </table>
        </section>
    </div>

    <script src="assets/js/admin-darkmode.js"></script>
</body>
</html>
