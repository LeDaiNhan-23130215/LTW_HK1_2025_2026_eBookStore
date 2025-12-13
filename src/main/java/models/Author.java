package models;

public class Author extends Base {
    private String name;
    private String authorDetail;

    public Author(int id, String name, String authorDetail) {
        super(id);
        this.name = name;
        this.authorDetail = authorDetail;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
