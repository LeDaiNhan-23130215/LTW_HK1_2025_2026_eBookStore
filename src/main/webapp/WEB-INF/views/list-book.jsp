<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="vi_VN"/>
<fmt:setTimeZone value="Asia/Ho_Chi_Minh"/>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>EBook Store</title>

  <link rel="stylesheet" href="assets/css/base.css"/>
  <link rel="stylesheet" href="assets/css/components.css"/>
  <link rel="stylesheet" href="assets/css/list-book.css"/>

  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>

  <link rel="icon" href="../../assets/img/ebook-logo2.png"/>
</head>

<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<main class="container">

  <!-- ================= FILTER SIDEBAR ================= -->
  <aside class="sidebar">
    <form method="get" action="list-book">

      <!-- Hidden inputs to preserve sorting when filtering -->
      <input type="hidden" name="sortBy" value="${filter.sortBy}">
      <input type="hidden" name="sortDir" value="${filter.sortDir}">

      <!-- ===== CATEGORY ===== -->
      <div class="filter">
        <h3>Thể loại</h3>

        <label>
          <input type="checkbox" name="category" value="1"
                 <c:if test="${filter.hasCategory(1)}">checked</c:if>>
          Tech
        </label>

        <label>
          <input type="checkbox" name="category" value="2"
                 <c:if test="${filter.hasCategory(2)}">checked</c:if>>
          Tham khảo
        </label>

        <label>
          <input type="checkbox" name="category" value="3"
                 <c:if test="${filter.hasCategory(3)}">checked</c:if>>
          Tiếng Anh
        </label>
      </div>

      <!-- ===== PRICE ===== -->
      <div class="filter">
        <h3>Giá</h3>

        <label>
          <input type="radio" name="free" value="true"
                 <c:if test="${filter.free == true}">checked</c:if>>
          Miễn phí
        </label>

        <label>
          <input type="radio" name="free" value="false"
                 <c:if test="${filter.free == false}">checked</c:if>>
          Có phí
        </label>

        <label>
          <input type="radio" name="free" value=""
                 <c:if test="${filter.free == null}">checked</c:if>>
          Tất cả
        </label>
      </div>

      <!-- ===== FORMAT ===== -->
      <div class="filter">
        <h3>Loại file</h3>

        <label>
          <input type="checkbox" name="format" value="PDF"
                 <c:if test="${filter.hasFormat('PDF')}">checked</c:if>>
          PDF
        </label>

        <label>
          <input type="checkbox" name="format" value="EPUB"
                 <c:if test="${filter.hasFormat('EPUB')}">checked</c:if>>
          EPUB
        </label>
      </div>

      <button type="submit" class="btn-filter">Lọc</button>
    </form>
  </aside>

  <!-- ================= MAIN CONTENT ================= -->
  <section class="content">

    <!-- ===== SORT BAR ===== -->
    <div class="sort-bar">

      <a href="list-book?sortBy=title&sortDir=asc<c:if test='${not empty queryStringForSort}'>&${queryStringForSort}</c:if>"
         class="sort-btn ${filter.sortBy == 'title' && filter.sortDir == 'asc' ? 'active' : ''}">
        <i class="fa-solid fa-arrow-down-a-z"></i> A - Z
      </a>

      <a href="list-book?sortBy=title&sortDir=desc<c:if test='${not empty queryStringForSort}'>&${queryStringForSort}</c:if>"
         class="sort-btn ${filter.sortBy == 'title' && filter.sortDir == 'desc' ? 'active' : ''}">
        <i class="fa-solid fa-arrow-up-a-z"></i> Z - A
      </a>

      <a href="list-book?sortBy=price&sortDir=asc<c:if test='${not empty queryStringForSort}'>&${queryStringForSort}</c:if>"
         class="sort-btn ${filter.sortBy == 'price' && filter.sortDir == 'asc' ? 'active' : ''}">
        <i class="fa-solid fa-arrow-up"></i> Giá ↑
      </a>

      <a href="list-book?sortBy=price&sortDir=desc<c:if test='${not empty queryStringForSort}'>&${queryStringForSort}</c:if>"
         class="sort-btn ${filter.sortBy == 'price' && filter.sortDir == 'desc' ? 'active' : ''}">
        <i class="fa-solid fa-arrow-down"></i> Giá ↓
      </a>

      <a href="list-book?sortBy=created_at&sortDir=desc<c:if test='${not empty queryStringForSort}'>&${queryStringForSort}</c:if>"
         class="sort-btn ${filter.sortBy == 'created_at' && filter.sortDir == 'desc' ? 'active' : ''}">
        <i class="fa-solid fa-calendar"></i> Mới nhất
      </a>

    </div>

    <!-- ===== PRODUCT GRID ===== -->
    <div class="product-grid">

      <c:forEach var="eb" items="${ebooks}">
        <div class="product-card">

          <div class="img-wrapper">
            <img src="<c:url value='${eb.imageLink}'/>"
                 alt="${eb.title}">
          </div>

          <p class="title">${eb.title}</p>

          <div class="price-box">
            <c:choose>
              <c:when test="${eb.price > 0}">
                <span class="price">
                  <fmt:formatNumber value="${eb.price}" type="currency"/>
                </span>
              </c:when>
              <c:otherwise>
                <span class="free">Free</span>
              </c:otherwise>
            </c:choose>

            <button class="add-to-cart">
              <i class="fa-solid fa-cart-plus"></i>
            </button>
          </div>
        </div>
      </c:forEach>

    </div>

    <!-- ===== PAGINATION ===== -->
    <div class="pagination">

      <c:if test="${currentPage > 1}">
        <a class="nav-btn"
           href="list-book?page=${currentPage - 1}<c:if test='${not empty queryString}'>&${queryString}</c:if>">
          «
        </a>
      </c:if>

      <c:forEach begin="1" end="${totalPages}" var="i">
        <a class="page-btn ${i == currentPage ? 'active' : ''}"
           href="list-book?page=${i}<c:if test='${not empty queryString}'>&${queryString}</c:if>">
            ${i}
        </a>
      </c:forEach>

      <c:if test="${currentPage < totalPages}">
        <a class="nav-btn"
           href="list-book?page=${currentPage + 1}<c:if test='${not empty queryString}'>&${queryString}</c:if>">
          »
        </a>
      </c:if>

    </div>

  </section>

</main>

<jsp:include page="/WEB-INF/views/footer.jsp"/>

<script src="assets/js/backToTopBtn.js"></script>

</body>
</html>