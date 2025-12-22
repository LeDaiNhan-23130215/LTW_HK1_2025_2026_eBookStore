package models;

public class Author extends Base {
    private String name;
    private String authorDetail;

    public Author(int id, String name, String authorDetail) {
        super(id);
        this.name = name;
        this.authorDetail = authorDetail;
    }

    public Author(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorDetail() {
        return authorDetail;
    }

    public void setAuthorDetail(String authorDetail) {
        this.authorDetail = authorDetail;
    }
}
