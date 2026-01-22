package services;

import DAO.ImageDAO;
import models.Author;
import models.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageServices {
    ImageDAO imageDAO = new ImageDAO();

    public Map<Integer, Image> getImages() {
        Map<Integer, Image> images = imageDAO.getImagesMap();
        return images != null ? images : new HashMap<>();
    }

    public int insertAndGetImgID(Image image) {
        return imageDAO.insertAndReturnId(image);
    }
}
