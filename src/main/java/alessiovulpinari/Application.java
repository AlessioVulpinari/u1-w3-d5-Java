package alessiovulpinari;

import alessiovulpinari.dao.CatalogueElementDao;
import alessiovulpinari.entities.Book;
import alessiovulpinari.entities.Magazine;
import alessiovulpinari.enums.Periodicity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1-w3-d5-Java");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogueElementDao catalogueElementDao = new CatalogueElementDao(em);

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
            System.out.println(leDueTorriFromDb);
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
    }
}
