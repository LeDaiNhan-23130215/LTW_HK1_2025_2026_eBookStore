package models;

import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Ebook extends Base {
    private String title;
    private double price;
    private String description;
    private int categoryID;
    private int fullFileID;
    private int demoFileID;
    private String status;
    private String eBookCode;
    private List<Image> images;
    private List<Author> authors;

    public Ebook(int id, String title, double price, String description, int categoryID, int fullFileID, int demoFileID, String status, String eBookCode) {
        super(id);
        this.title = title;
        this.price = price;
        this.description = description;
        this.fullFileID = fullFileID;
        this.status = status;
        this.demoFileID = demoFileID;
        this.categoryID = categoryID;
        this.eBookCode = eBookCode;
    }

    public Ebook(String title, double price, String description, int categoryID, int fullFileID, int demoFileID, String status, String eBookCode) {
        super(-1);
        this.title = title;
        this.price = price;
        this.description = description;
        this.fullFileID = fullFileID;
        this.status = status;
        this.demoFileID = demoFileID;
        this.categoryID = categoryID;
        this.eBookCode = eBookCode;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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

    public String geteBookCode() {
        return eBookCode;
    }

    public void setBookCode(String eBookCode) {
        this.eBookCode = eBookCode;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
