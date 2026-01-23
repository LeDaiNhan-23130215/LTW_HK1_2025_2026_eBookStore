package services;

import DAO.ImageDAO;
import models.Author;
import models.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageServices {
    ImageDAO imageDAO = new ImageDAO();

    public List<Image> getImagesByEbookID(int ebookID) {
        return imageDAO.getByEbookID(ebookID);
    }

    public Image getThumbnail(int ebookID) {
        return imageDAO.getFirstImageByEbookID(ebookID);
    }

    public void insertImages(int ebookID, List<String> urls, String ebookTitle) {
        for (String url : urls) {
            Image img = new Image(
                    ebookID,
                    ebookTitle,
                    url,
                    "ACTIVE"
            );
            imageDAO.insert(img);
        }
    }

    public void insert(Image image) {
        imageDAO.insert(image);
    }
}
