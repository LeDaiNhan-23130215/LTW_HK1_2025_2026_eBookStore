package mappers;

import models.Author;

public class AuthorCSVMapper implements CSVMapper<Author>{
    @Override
    public Author map(String[] cols) {
        String authorName = cols[0].trim();
        String authorDetail = cols[1].trim();
        int birthYear = Integer.parseInt(cols[2].trim());
        String nationality = cols[3].trim();
        int numberOfBooks = Integer.parseInt(cols[4].trim());
        String awards = cols[5].trim();

        if (authorName.isEmpty()) return null;

        return new Author(authorName, authorDetail, birthYear, nationality, numberOfBooks, awards);
    }
}
