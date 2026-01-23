package models;

public class Image extends Base {
    private int ebookID;
    private String imgName;
    private String imgLink;
    private String imgStatus;

    public Image(int id, int ebookID, String imgName, String imgLink, String imgStatus) {
        super(id);
        this.ebookID = ebookID;
        this.imgName = imgName;
        this.imgLink = imgLink;
        this.imgStatus = imgStatus;
    }

    public Image(int ebookID, String imgName, String imgLink, String imgStatus) {
        super(-1);
        this.ebookID = ebookID;
        this.imgName = imgName;
        this.imgLink = imgLink;
        this.imgStatus = imgStatus;
    }

    public Image(String imgName, String imgLink, String imgStatus) {
        super(-1);
        this.imgName = imgName;
        this.imgLink = imgLink;
        this.imgStatus = imgStatus;
    }

    public Image(int id) {
        super(id);
    }

    public int getEbookID() {
        return ebookID;
    }

    public void setEbookID(int ebookID) {
        this.ebookID = ebookID;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setImgStatus(String imgStatus) {
        this.imgStatus = imgStatus;
    }
}
