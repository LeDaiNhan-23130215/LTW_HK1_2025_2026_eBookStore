package controllers;

import DTO.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.EbookService;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "ListbookController", value = "/list-book")
public class ListbookController extends HttpServlet {

    private final EbookService ebookService = new EbookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", ebookService.getAllCategories());

        // ===== PAGE =====
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // ===== FILTER OBJECT =====
        EbookFilterView filter = new EbookFilterView();

        // ===== FREE / PAID =====
        String freeParam = request.getParameter("free");
        if (freeParam != null && !freeParam.isEmpty()) {
            if (freeParam.equals("true")) {
                filter.setFree(true);
            } else if (freeParam.equals("false")) {
                filter.setFree(false);
            }
        } else {
            filter.setFree(null);
        }

        // ===== CATEGORY (checkbox) =====
        String[] categories = request.getParameterValues("category");
        if (categories != null && categories.length > 0) {
            filter.setCategoryId(
                    Arrays.stream(categories)
                            .map(Integer::parseInt)
                            .toList()
            );
        }

        // ===== FORMAT (checkbox) =====
        String[] formats = request.getParameterValues("format");
        if (formats != null && formats.length > 0) {
            filter.setFormats(Arrays.asList(formats));
        }

        // ===== SORTING =====
        String sortBy = request.getParameter("sortBy");
        String sortDir = request.getParameter("sortDir");

        // ===== SEARCH =====
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.isEmpty()) {
            filter.setKeywords(keyword);
        }

        if (sortBy != null && !sortBy.isEmpty()) {
            filter.setSortBy(sortBy);
        } else {
            filter.setSortBy("created_at"); // default sort
        }

        if (sortDir != null && !sortDir.isEmpty()) {
            filter.setSortDir(sortDir);
        } else {
            filter.setSortDir("desc"); // default direction
        }

        // ===== BUILD QUERY STRINGS =====
        // For pagination (excludes page only)
        String queryStringForPagination = buildQueryString(request, true, true);
        request.setAttribute("queryString", queryStringForPagination);

        // For sort buttons (excludes page, sortBy, sortDir)
        String queryStringForSort = buildQueryString(request, false, false);
        request.setAttribute("queryStringForSort", queryStringForSort);


        // ===== GET DATA =====
        PageView<EbookProductCardView> pageView = ebookService.getBooks(page, filter);

        // ===== SET ATTRIBUTES =====
        request.setAttribute("pageView", pageView);
        request.setAttribute("newEBooks", pageView.getItems());
        request.setAttribute("filter", filter);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", pageView.getTotalPages());


        // ==== AJAX ====
        boolean isAjax = "XMLHttpRequest".equals(
                request.getHeader("X-Requested-With")
        );

        if (isAjax) {
            request.getRequestDispatcher(
                    "/WEB-INF/views/list-book-grid.jsp"
            ).forward(request, response);
        } else {
            request.getRequestDispatcher(
                    "/WEB-INF/views/list-book.jsp"
            ).forward(request, response);
        }
    }

    /**
     * Build query string
     * @param includeSorting - include sortBy and sortDir in query string
     * @param includeFilters - include filter parameters in query string
     */
    private String buildQueryString(HttpServletRequest request,
                                    boolean includeSorting,
                                    boolean includeFilters) {
        StringBuilder sb = new StringBuilder();

        if (includeFilters) {
            // Free/Paid
            String freeParam = request.getParameter("free");
            if (freeParam != null && !freeParam.isEmpty()) {
                sb.append("free=").append(freeParam).append("&");
            }

            // Categories
            String[] categories = request.getParameterValues("category");
            if (categories != null) {
                for (String cat : categories) {
                    sb.append("category=").append(cat).append("&");
                }
            }

            // Formats
            String[] formats = request.getParameterValues("format");
            if (formats != null) {
                for (String fmt : formats) {
                    sb.append("format=").append(fmt).append("&");
                }
            }
        }

        if (includeSorting) {
            // Sorting
            String sortBy = request.getParameter("sortBy");
            String sortDir = request.getParameter("sortDir");

            if (sortBy != null && !sortBy.isEmpty()) {
                sb.append("sortBy=").append(sortBy).append("&");
            }
            if (sortDir != null && !sortDir.isEmpty()) {
                sb.append("sortDir=").append(sortDir).append("&");
            }
        }

        // Search keyword
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.isEmpty()) {
            sb.append("keyword=").append(keyword).append("&");
        }


        // Remove trailing &
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '&') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}