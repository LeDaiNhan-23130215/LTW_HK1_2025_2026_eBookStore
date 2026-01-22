package models;

public class DemoFile extends Base{
    private String fileName;
    private String fileFormat;
    private long fileSize;
    private String fileLink;
    private int limitPage;
    private String fileStatus;

    public DemoFile(String fileName, String fileFormat, long fileSize, String fileLink, int limitPage, String fileStatus) {
        super(-1);
        this.fileName = fileName;
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
        this.fileLink = fileLink;
        this.limitPage = limitPage;
        this.fileStatus = fileStatus;
    }

    public DemoFile(int id) {
        super(id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public int getLimitPage() {
        return limitPage;
    }

    public void setLimitPage(int limitPage) {
        this.limitPage = limitPage;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }
}
