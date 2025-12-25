<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Danh mục</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link rel="stylesheet" href="assets/css/category.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
    
    <main class="main-filters">
      <div class="main-container">
        <!-- Banner bên trái -->
        <div class="side-banner">
          <img
            src="https://tse2.mm.bing.net/th/id/OIP.TgM9m1BkUQxyAeISQc3SqwHaLY?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
            alt="banner"
          />
        </div>

        <!-- Nội dung chính -->
        <div class="main-content">
          <h2>Danh mục</h2>
          <div class="filter-row">
            <div class="filter-group">
              <h3>Thể loại:</h3>
              <div class="filter-option">Tiểu thuyết</div>
              <div class="filter-option">Khoa học</div>
              <div class="filter-option">Kinh doanh</div>
              <div class="filter-option">Thiếu nhi</div>
            </div>

            <div class="filter-group">
              <h3>Định dạng:</h3>
              <div class="filter-option">PDF</div>
              <div class="filter-option">EPUB</div>
              <div class="filter-option">MOBI</div>
            </div>

            <div class="filter-group">
              <h3>Giá:</h3>
              <div class="filter-option">Miễn phí</div>
              <div class="filter-option">Trả phí</div>
            </div>
          </div>

          <a href="list-book" class="btn-filter">Lọc sách</a>
        </div>

        <!-- Banner bên phải -->
        <div class="side-banner">
          <img
            src="https://tse2.mm.bing.net/th/id/OIP.TgM9m1BkUQxyAeISQc3SqwHaLY?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
            alt="banner"
          />
        </div>
      </div>
    </main>

    <jsp:include page="/WEB-INF/views/footer.jsp"/>

    <script src="assets/js/category.js"></script>
    <script src="assets/js/component.js"></script>
    <script src="assets/js/backToTopBtn.js"></script>
  </body>
</html>
