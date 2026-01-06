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

    public Ebook(int id) {
        super(id);
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

    public String getEbookNum() {
        return ebookNum;
    }

    public void setEbookNum(String ebookNum) {
        this.ebookNum = ebookNum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFullFileID() {
        return fullFileID;
    }

    public void setFullFileID(int fullFileID) {
        this.fullFileID = fullFileID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getDemoFileID() {
        return demoFileID;
    }

    public void setDemoFileID(int demoFileID) {
        this.demoFileID = demoFileID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ebook)) return false;
        Ebook ebook = (Ebook) o;
        return this.id == ebook.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
