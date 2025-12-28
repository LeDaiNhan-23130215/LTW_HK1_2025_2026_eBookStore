<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="product-grid">
    <c:forEach var="eb" items="${newEBooks}">
        <div class="product-card">
            <div class="img-wrapper">
                <img src="<c:url value='${eb.imageLink}'/>"
                     alt="${eb.title}">
            </div>

            <p>${eb.title}</p>

            <div>
                <c:if test="${eb.price != null and eb.price gt 0}">
                <span class = "price">
                    <fmt:formatNumber value="${eb.price}"
                                      type="currency"
                                      groupingUsed="true"/>
                </span>
                </c:if>

                <c:if test="${eb.price eq 0}">
                    <span>Free!!!</span>
                </c:if>

                <form action="cart" method="post" class="add-to-cart-form">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="bookId" value="${eb.id}">
                    <input type="hidden" name="price" value="${eb.price}">
                    <button type="submit" class="add-to-cart-btn">
                        <i class="fa-solid fa-cart-plus"></i>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

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