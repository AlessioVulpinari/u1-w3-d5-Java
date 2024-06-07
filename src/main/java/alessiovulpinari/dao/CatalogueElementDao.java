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

    public void save(CatalogueElement catalogueElement) {
        // Aprire la transazione
        EntityTransaction transaction = entityManager.getTransaction();

        // Iniziare la transazione
        transaction.begin();

        // Aggiungere al persist Context
        entityManager.persist(catalogueElement);

        // Chiudere la transazione, salvando nel db
        transaction.commit();
        System.out.println("L'elemento " + catalogueElement.getTitle() + " è stato aggiunto correttamente al db!");
    }

    public CatalogueElement getByIsbn(String isbn) {
        CatalogueElement found = entityManager.find(CatalogueElement.class, UUID.fromString(isbn));

        if (found == null) throw new NotFoundExceptions(isbn);

        return found;
    }

    public void deleteByIsbn(String isbn) {
        // Cerchiamo l'elemento
        CatalogueElement foundElement = getByIsbn(isbn);

        // Creo la transazione
        EntityTransaction transaction = entityManager.getTransaction();

        // Inizializzo la transazione
        transaction.begin();

        // Rimuovo l'evento
        entityManager.remove(foundElement);

        // Chiudo la transazione e salvo il cambiamento
        transaction.commit();

        System.out.println("L'elemento: " + foundElement.getTitle() + " è stato eliminato correttamente al db!");
    }

    public List<CatalogueElement> searchByYear(int year) {

        TypedQuery<CatalogueElement> query = entityManager.createQuery("SELECT c FROM CatalogueElement c WHERE c.yearOfPublication = :year",
                CatalogueElement.class);
        query.setParameter("year", year);

        return query.getResultList();
    }

    public List<Book> searchByAuthor(String author) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:author)",
                Book.class);
        query.setParameter("author", author);

        return query.getResultList();
    }

    public List<CatalogueElement> searchByTitle(String title) {
        TypedQuery<CatalogueElement> query = entityManager.createQuery("SELECT c FROM CatalogueElement c WHERE LOWER(c.title) LIKE LOWER(:title)",
                CatalogueElement.class);
        query.setParameter("title", "%" + title + "%");

        return query.getResultList();
    }
}
