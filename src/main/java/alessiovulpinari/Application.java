package alessiovulpinari;

import alessiovulpinari.dao.CatalogueElementDao;
import alessiovulpinari.dao.LoanDao;
import alessiovulpinari.dao.UserDao;
import alessiovulpinari.entities.*;
import alessiovulpinari.enums.Periodicity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1-w3-d5-Java");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogueElementDao catalogueElementDao = new CatalogueElementDao(em);
        LoanDao loanDao = new LoanDao(em);
        UserDao userDao = new UserDao(em);

        Book leDueTorri = new Book("Il signore degli anelli: le due torri", 1954, 509,
                "J.R.R. Tolkien", "Fantasy");

        Magazine donnaModerna = new Magazine("Donna moderna", 2024,
                100, Periodicity.MONTHLY);

        /* Test to save in the db
        catalogueElementDao.save(leDueTorri);
        catalogueElementDao.save(donnaModerna);*/

        /* Test to find an element by his ISBN
        try {
            CatalogueElement leDueTorriFromDb = catalogueElementDao.getByIsbn("fdb7f233-1728-4b77-ae1e-2500006b23ba");
        } catch (NotFoundExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        } */

        /* Test To delete and element
        try {
            catalogueElementDao.deleteByIsbn("748a60cc-e506-41fc-9be3-844ce29ba773");
        } catch (NotFoundExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        } */

        /* Test to Search by Year
        List<CatalogueElement> list = catalogueElementDao.searchByYear(1954);
        list.forEach(System.out::println); */

        /* Search by Author
        List<Book> books = catalogueElementDao.searchByAuthor("J.R.R. Tolkien");
        books.forEach(System.out::println); */

        /* Search by Title
        List<CatalogueElement> catalogueElementList = catalogueElementDao.searchByTitle("Il signore degli");
        catalogueElementList.forEach(System.out::println);  */

        CatalogueElement leDueTorriFromDb = catalogueElementDao.getByIsbn("2d4bb0d0-1242-472f-b08b-49f3a7832c3b");
        User davide = new User("Davide", "Luigini", LocalDate.now());
        Loan loan = new Loan(LocalDate.now(), null, userDao.getByCardId("a941cc09-fd49-43a4-b384-b49a366533be"), leDueTorriFromDb);

//        userDao.save(davide);
//        loanDao.save(loan);

        List<CatalogueElement> catalogueElementList = loanDao.searchByInLoan("a941cc09-fd49-43a4-b384-b49a366533be");
        catalogueElementList.forEach(System.out::println);
    }
}
