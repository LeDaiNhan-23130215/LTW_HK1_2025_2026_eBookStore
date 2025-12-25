package services;

import DAO.EbookDAO;
import DAO.ImageDAO;
import DTO.EbookFilterView;
import DTO.EbookProductCardView;
import DTO.PageView;
import models.Ebook;
import models.Image;

import java.util.ArrayList;
import java.util.List;

public class EbookService {
    private EbookDAO ebookDAO = new EbookDAO();
    private ImageDAO imageDAO = new ImageDAO();

    public List<EbookProductCardView> getProductCards() {
        List<Ebook> ebooks = ebookDAO.findAll();
        List<EbookProductCardView> result = new ArrayList<EbookProductCardView>();

        for(Ebook e : ebooks) {
            Image img = imageDAO.getImageById(e.getImageID());

            result.add(new EbookProductCardView(e.getId(), e.getTitle(), e.getPrice(), img.getImgLink()));
        }
        return result;
    }

    public List<EbookProductCardView> getNewEbookProductCards() {
        List<Ebook> ebooks = ebookDAO.getNewBook();
        return getEbookProductCardViews(ebooks);
    }

    private static final int PAGE_SIZE = 24;
    public PageView<EbookProductCardView> getBooks(
            int page, EbookFilterView filter) {

        if (page < 1) page = 1;

        int totalItems = ebookDAO.countProductCards(filter);
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        if (page > totalPages && totalPages > 0) {
            page = totalPages;
        }

        List<EbookProductCardView> items =
                ebookDAO.findProductCards(page, PAGE_SIZE, filter);

        return new PageView<>(items, page, totalPages);
    }
    public int getPageSize() {
        return PAGE_SIZE;
    }

    private List<EbookProductCardView> getEbookProductCardViews(List<Ebook> ebooks) {
        List<EbookProductCardView> result = new ArrayList<>();
        for (Ebook e : ebooks) {
            Image img = imageDAO.getImageById(e.getImageID());

            result.add(new EbookProductCardView(
                    e.getId(),
                    e.getTitle(),
                    e.getPrice(),
                    img.getImgLink()
            ));
        }
        return result;
    }

    public static void main(String[] args) {
        EbookService ebookService = new EbookService();

    }
}
