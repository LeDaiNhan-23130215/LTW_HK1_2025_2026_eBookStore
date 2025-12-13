<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Ebook Manager</title>
    <link rel="stylesheet" href="assets/css/admin-ebook.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
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
<body>
    <!--Side bar-->
    <aside class="sidebar">
        <div class="sidebar-logo">
            <h2>Ebook Admin</h2>
        </div>

        <nav class="sidebar-nav">
            <a href="${pageContext.request.contextPath}/admin-dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin-ebook" class="active">Ebook</a>
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
            <div class="topbar-title">Quản lý eBook</div>
            <button id="toggle-theme">🌙 Dark Mode</button>
        </header>

        <!--Dashboard-->
        <section class="dashboard">
            <div class="add-form">
                <h2 class="toggle-title"><i class="fa-solid fa-plus"></i> Thêm sách mới</h2>
                <form action="" id="addBookForm">
                    <div class="form-row">
                        <label for="bookName">Tên sách: </label>
                        <input type="text" id="bookName" placeholder="Tên sách" required>
                    </div>
                    <div class="form-row">
                        <label for="author">Tác giả: </label>
                        <input type="text" id="author" placeholder="Tác giả" required>
                    </div>
                    <div class="form-row">
                        <label for="category">Thể loại: </label>
                        <select name="genre" id="category" required>
                            <option value="">--Chọn thể loại--</option>
                            <option>Sách khoa học</option>
                            <option>Sách nấu ăn</option>
                            <option>Sách tham khảo</option>
                            <option>Sách AI</option>
                            <option>Sách tiếng Anh</option>
                            <option>Sách kĩ năng sống</option>
                        </select>
                    </div>
                    <div class="form-row">
                        <label for="price">Giá: </label>
                        <input type="text" id="price" placeholder="Giá" required>
                    </div>
                    <div class="form-row">
                        <label for="url">Ảnh bìa (URL): </label>
                        <input type="text" id="url" placeholder="Ảnh bìa (URL)" required>
                    </div>
                    <div class="banner-preview">
                        <img id="banner-preview-img" src="" alt="Xem trước banner" style="display:none;">
                    </div>
                    <div class="form-row">
                        <label for="file">File: </label>
                        <input type="text" id="file" placeholder="File" required>
                    </div>
                    <div class="form-row">
                        <label for="description">Mô tả: </label>
                        <input type="text" id="description" placeholder="Mô tả" required>
                    </div>

                    <button type="submit" class="btn-addEbook">Thêm sách</button>
                </form>
            </div>
        </section>

        <!-- Danh sách eBook-->
        <section class="table-section">
            <table id="activityTable" class="table table-striped table-bordered">
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
                <tbody id="eBookTableBody">
                    <tr>
                        <td>1</td>
                        <td>Deep Work</td>
                        <td>Cal Newport</td>
                        <td>Sách kĩ năng sống</td>
                        <td>280.000 VND</td>
                        <td>
                            <button class="btn-Edit"><i class="fa-solid fa-pen-to-square"></i> Sửa</button>
                            <button class="btn-Del"><i class="fa-solid fa-trash"></i> Xóa</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>
    </div>
    <script src="assets/js/admin-darkmode.js"></script>
    <script src="assets/js/admin-demoIMG.js"></script>
    <script src="assets/js/showForm.js"></script>
</body>
</html>