package alessiovulpinari.dao;

import alessiovulpinari.entities.Book;
import alessiovulpinari.entities.CatalogueElement;
import alessiovulpinari.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class CatalogueElementDao {

    private final EntityManager entityManager;

    public CatalogueElementDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save a CatalogueElement into the DB
    public void save(CatalogueElement catalogueElement) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(catalogueElement);
        transaction.commit();
        System.out.println("L'elemento " + catalogueElement.getTitle() + " è stato aggiunto correttamente al db!");
    }

    // Return a CatalogueElement based on the passed parameter
    public CatalogueElement getByIsbn(String isbn) {
        CatalogueElement found = entityManager.find(CatalogueElement.class, UUID.fromString(isbn));

        if (found == null) throw new NotFoundExceptions(isbn);

        return found;
    }

    // Delete a CatalogueElement from the DB based on the passed parameter
    public void deleteByIsbn(String isbn) {
        CatalogueElement foundElement = getByIsbn(isbn);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(foundElement);
        transaction.commit();
        System.out.println("L'elemento: " + foundElement.getTitle() + " è stato eliminato correttamente al db!");
    }

    // Search by year of publication
    public List<CatalogueElement> searchByYear(int year) {

        TypedQuery<CatalogueElement> query = entityManager.createQuery("SELECT c FROM CatalogueElement c WHERE c.yearOfPublication = :year",
                CatalogueElement.class);
        query.setParameter("year", year);

        return query.getResultList();
    }

    // Search by author
    public List<Book> searchByAuthor(String author) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:author)",
                Book.class);
        query.setParameter("author", author);

        return query.getResultList();
    }

    // Search by title
    public List<CatalogueElement> searchByTitle(String title) {
        TypedQuery<CatalogueElement> query = entityManager.createQuery("SELECT c FROM CatalogueElement c WHERE LOWER(c.title) LIKE LOWER(:title)",
                CatalogueElement.class);
        query.setParameter("title", "%" + title + "%");

        return query.getResultList();
    }
}
