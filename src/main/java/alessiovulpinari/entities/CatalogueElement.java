package alessiovulpinari.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "elementi_catalogo")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "elemento_catalogo")
public abstract class CatalogueElement {

    @Id
    @GeneratedValue
    protected UUID isbn;

    @Column(name = "titolo", nullable = false)
    protected String title;

    @Column(name = "anno_di_pubblicazione", nullable = false)
    protected int yearOfPublication;

    @Column(name = "numero_di_pagine", nullable = false)
    protected int pages;

    @OneToMany(mappedBy = "catalogueElement")
    protected List<Loan> loans;

    public CatalogueElement() {
    }

    public CatalogueElement(String title, int yearOfPublication, int pages) {
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.pages = pages;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
