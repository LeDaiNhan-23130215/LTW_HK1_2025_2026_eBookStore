package models;

public class Image {
    private int id;
    private String imgName;
    private String imgLink;
    private String imgStatus;
    public Image(int id, String imgName, String imgLink, String imgStatus) {
        this.id = id;
        this.imgName = imgName;
        this.imgLink = imgLink;
        this.imgStatus = imgStatus;
    }

    public int getId() {
        return id;
    }

    public String getImgName() {
        return imgName;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getImgStatus() {
        return imgStatus;
    }
}
