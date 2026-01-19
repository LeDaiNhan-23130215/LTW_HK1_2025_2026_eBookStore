package models;

public class Author extends Base {
    private String authorName;
    private String authorDetail;
    private int birthYear;
    private String nationality;
    private int numberOfBooks;
    private String awards;

    public Author(int id, String authorName, String authorDetail,  int birthYear, String nationality, int numberOfBooks, String awards) {
        super(id);
        this.authorName = authorName;
        this.authorDetail = authorDetail;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.numberOfBooks = numberOfBooks;
        this.awards = awards;
    }

    public Author(int id) {
        super(id);
    }

    public Author(String authorName, String authorDetail,  int birthYear, String nationality, int numberOfBooks, String awards) {
        super(-1);
        this.authorName = authorName;
        this.authorDetail = authorDetail;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.numberOfBooks = numberOfBooks;
        this.awards = awards;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorDetail() {
        return authorDetail;
    }

    public void setAuthorDetail(String authorDetail) {
        this.authorDetail = authorDetail;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }
}
