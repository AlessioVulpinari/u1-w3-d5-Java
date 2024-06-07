package alessiovulpinari.dao;

import alessiovulpinari.entities.User;
import alessiovulpinari.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UserDao {
    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save a User in the DB
    public void save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        System.out.println("L'utente: " + user.getLastName() + " è stato aggiunto correttamente al db!");
    }

    // Return a User based on the passed parameter
    public User getByCardId(String cardId) {
        User found = entityManager.find(User.class, UUID.fromString(cardId));

        if (found == null) throw new NotFoundExceptions(cardId);

        return found;
    }

    // Delete a User from the DB based on the passed parameter
    public void deleteByCardId(String cardId) {
        User foundUser = getByCardId(cardId);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(foundUser);
        transaction.commit();
        System.out.println("L'utente: " + foundUser.getLastName() + " è stato eliminato correttamente al db!");
    }
}
