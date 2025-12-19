package models;

import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Ebook extends Base {
    private String ebookNum;
    private String title;
    private int  authorID;
    private double price;
    private int imageID;
    private String description;
    private int categoryID;
    private int fullFileID;
    private int demoFileID;
    private String status;

    public Ebook(int id, String title, int authorID, double price, int imageID, String description, int categoryID, int fullFileID, int demoFileID, String status) {
        super(id);
        this.title = title;
        this.authorID = authorID;
        this.price = price;
        this.imageID = imageID;
        this.description = description;
        this.fullFileID = fullFileID;
        this.status = status;
        this.demoFileID = demoFileID;
        this.categoryID = categoryID;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public  String getTitle() {
        return this.title;
    }

    public double getPrice() {
        return this.price;
    }

    public int getImageID() {
        return this.imageID;
    }
}
