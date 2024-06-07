package alessiovulpinari.dao;

import alessiovulpinari.entities.CatalogueElement;
import alessiovulpinari.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

    public CatalogueElement getByIsbn(UUID isbn) {
        CatalogueElement found = entityManager.find(CatalogueElement.class, isbn);

        if (found == null) throw new NotFoundExceptions(isbn);

        return found;
    }

    public void deleteByIsbn(UUID isbn) {
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
}
