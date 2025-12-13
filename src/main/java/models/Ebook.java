package models;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Locale;

public class Ebook extends Base {
    private String ebookNum;
    private String title;
    private Author author;
    private int price;
    private List<EbookImage> images;
    private String description;
    private int categoryID;
    private List<File> file;

    public Ebook(int id,String ebookNum, String title, Author author, int price, List<EbookImage> images, String description){
        super(id);
    };

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
