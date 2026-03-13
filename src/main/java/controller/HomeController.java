package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HomeController", value = "/Home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        List<Category> randomCategory = ctDAO.getRandom5Categories();
        request.setAttribute("randomCategory", randomCategory);

        List<EbookProductCardView> newEBooks = ebookService.getNewEbookProductCards();
        request.setAttribute("newEBooks", newEBooks);

        List<EbookProductCardView> topSaleEbooks = ebookService.getTopSaleEbookProductCards();
        request.setAttribute("topSaleEBooks", topSaleEbooks);

        List<EbookProductCardView> randomEbook = ebookService.getRandomEbook();
        request.setAttribute("randomEbook", randomEbook);

        String imgRandom1 = randomEbook.get(0).getImageLink();
        request.setAttribute("rdBookimg1",imgRandom1);

        String imgRandom2 = randomEbook.get(1).getImageLink();
        request.setAttribute("rdBookimg2",imgRandom2);

        Banner imgHomeTop = bannerDAO.getBannerByLocation("homeTop");
        request.setAttribute("homeTop", imgHomeTop);
        // Chỉ lấy wishlist nếu đã login
        if (session != null && session.getAttribute("userID") != null) {
            int userID = (Integer) session.getAttribute("userID");
            List<Integer> wishlistIds = wishlistService.getWishlist(userID)
                    .stream()
                    .map(Ebook::getId)
                    .toList();
            request.setAttribute("wishlistIds", wishlistIds);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}