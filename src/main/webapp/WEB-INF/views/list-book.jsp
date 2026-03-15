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

    <link rel="stylesheet" href="<c:url value='/assets/css/base.css' />">
    <link rel="stylesheet" href="<c:url value='/assets/css/components.css' />">
    <link rel="stylesheet" href="<c:url value='/assets/css/list-book.css' />">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>

    <link rel="icon" type="image/png"
          href="<c:url value='/assets/img/ebook-logo2.png' />">
</head>

<body>

<button id="backToTopBtn" class="back-to-top">
    <i class="fa-solid fa-arrow-up"></i>
</button>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<main class="container">
    <aside class="sidebar">
        <form id="filterForm" method="get" action="list-book">

            //TODO
        </form>
    </aside>

    <!-- ================= MAIN CONTENT ================= -->
    <section class="content">
        //TODO

        <div id="active-filters" class="active-filters"></div>
        <div id="grid-container">
            <jsp:include page="/WEB-INF/views/list-book-grid.jsp"/>
        </div>

    </section>

</main>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
<script src="<c:url value='/assets/js/product-card.js' />"></script>

<script>
    const ctx = "${pageContext.request.contextPath}";
</script>

<script src="<c:url value='/assets/js/backToTopBtn.js' />"></script>
<script src="<c:url value='/assets/js/component.js' />"></script>
<script src="<c:url value='/assets/js/hamburger-button.js' />"></script>

<script src="<c:url value='/assets/js/list-book-pagination.js' />"></script>

<script src="<c:url value='/assets/js/list-book-filter.js' />"></script>
</body>
</html>