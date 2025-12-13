package models;

public class EbookImage extends Base {
    private String name;
    private String link;
    private String status;

    public EbookImage (int id, String name, String link, String status) {
        super(id);
        this.name = name;
        this.link = link;
        this.status = status;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
