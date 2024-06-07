package alessiovulpinari.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("libro")
public class Book extends CatalogueElement {

    @Column(name = "autore", nullable = false)
    private String author;
    @Column(name = "genere", nullable = false)
    private String genre;

    public Book() {
    }

    public Book(String title, int yearOfPublication, int pages, String author, String genre) {
        super(title, yearOfPublication, pages);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", dateOfPublication=" + yearOfPublication +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
