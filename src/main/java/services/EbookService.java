package services;

import DAO.EbookDAO;
import DAO.ImageDAO;
import DTO.EbookProductCardView;
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

    }
}
