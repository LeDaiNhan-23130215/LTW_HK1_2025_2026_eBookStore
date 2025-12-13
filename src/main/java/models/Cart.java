package models;

public class Cart extends Base {
    private int userID;

    public Cart(int id) {
        super(id);
    }

    public Cart(int id, int userID) {
        super(id);
        this.userID = userID;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
