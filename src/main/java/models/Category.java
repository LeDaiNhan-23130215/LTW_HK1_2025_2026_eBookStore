package models;

public class Category extends Base {
    private String name;
    private String description;

    public Category(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description) {
        super(-1);
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
