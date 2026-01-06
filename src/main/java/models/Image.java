package models;

public class Image extends Base {
    private String imgName;
    private String imgLink;
    private String imgStatus;

    public Image(int id, String imgName, String imgLink, String imgStatus) {
        super(id);
        this.imgName = imgName;
        this.imgLink = imgLink;
        this.imgStatus = imgStatus;
    }

    public Image(int id) {
        super(id);
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {

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

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setImgStatus(String imgStatus) {
        this.imgStatus = imgStatus;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
