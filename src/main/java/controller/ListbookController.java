package controller;

import DTO.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Category;
import services.EbookService;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "ListbookController", value = "/list-book")
public class ListbookController extends HttpServlet {

    private final EbookService ebookService = new EbookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //TODO
    }

    private String buildQueryString(HttpServletRequest request,
                                    EbookFilterView filter,
                                    boolean includeSorting,
                                    boolean includeFilters) {
        StringBuilder sb = new StringBuilder();

        if (includeFilters) {
            // Free/Paid
            String freeParam = request.getParameter("free");
            if (freeParam != null && !freeParam.isEmpty()) {
                sb.append("free=").append(freeParam).append("&");
            }
            //TODO
        }
        return "";
    }}