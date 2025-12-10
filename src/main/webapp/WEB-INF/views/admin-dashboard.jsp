<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
      <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />

    <!-- Bootstrap 5 -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">



    <!-- DataTables + Bootstrap 5 theme -->
    <link rel="stylesheet"
          href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <!-- Admin Dashboard theme -->
    <link rel="stylesheet" href="assets/css/admin-dashboard.css" />
    <!-- jQuery + DataTables -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
    <!--Side bar-->
    <aside class="sidebar">
        <div class="sidebar-logo">
            <h2>Ebook Admin</h2>
        </div>

        <nav class="sidebar-nav">
            <a href="${pageContext.request.contextPath}/admin-dashboard" class="active">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin-ebook">Ebook</a>
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

    <!--Main content-->
    <div class="main-content">
        <!--Top bar-->
        <header class="topbar">
            <div class="topbar-title">Bảng điều khiển</div>
            <div class="topbar-actions">
                <span class="admin-name">Xin chào, Admin</span>
                <button id="toggle-theme">🌙 Dark Mode</button>
            </div>
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

        <!--Dashboard content-->
        <section class="dashboard">
            <div class="cards">
                <div class="card" id="totalEbooks">
                    <h3>Tổng số eBook</h3>
                    <p id="total-books">200</p>
                </div>
                <div class="card" id="totalUsers">
                    <h3>Người dùng</h3>
                    <p id="total-users">20</p>
                </div>
                <div class="card" id="totalOrders">
                    <h3>Đơn hàng</h3>
                    <p id="total-orders">20</p>
                </div>
                <div class="card" id="totalRevenue">
                    <h3>Doanh thu tháng</h3>
                    <p id="total-revenue">2.000.000 VND</p>
                </div>
            </div>
        </section>

        <!-- Chart -->
        <section class="chart-section">
            <h2>Biểu đồ doanh thu</h2>
            <div class="chart-container">
                <canvas id="revenueChart"></canvas>
            </div>
        </section>

        <!-- Recent activity table -->
        <section class="table-section">
            <table id="activityTable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Ngày</th>
                    <th>Người dùng</th>
                    <th>Hoạt động</th>
                    <th>Chi tiết</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>21/10/2025</td>
                    <td>nguyenkhanh</td>
                    <td>Mua Ebook</td>
                    <td>"Clean Code"</td>
                </tr>
                <tr>
                    <td>20/10/2025</td>
                    <td>admin</td>
                    <td>Thêm banner mới</td>
                    <td>Black Friday Sale</td>
                </tr>
                <tr>
                    <td>19/10/2025</td>
                    <td>huynguyen</td>
                    <td>Đăng ký tài khoản</td>
                    <td>-</td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
    <script src="assets/js/admin-darkmode.js"></script>
    <script src="assets/js/admin-dashboard.js"></script>
</body>
</html>