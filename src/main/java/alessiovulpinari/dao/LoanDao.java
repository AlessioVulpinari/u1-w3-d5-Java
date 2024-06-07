package alessiovulpinari.dao;

import alessiovulpinari.entities.Loan;
import alessiovulpinari.exceptions.NotFoundExceptions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class LoanDao {
    private final EntityManager entityManager;

    public LoanDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();
        System.out.println("Il prestito: " + loan.getLoanId() + " è stato aggiunto correttamente al db!");
    }

    public Loan getByLoanId(String loanId) {
        Loan found = entityManager.find(Loan.class, UUID.fromString(loanId));

        if (found == null) throw new NotFoundExceptions(loanId);

        return found;
    }

    public void deleteByCardId(String loanId) {
        Loan foundLoan = getByLoanId(loanId);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(foundLoan);
        transaction.commit();
        System.out.println("Il prestito " + foundLoan.getLoanId() + " è stato eliminato correttamente al db!");
    }
}
