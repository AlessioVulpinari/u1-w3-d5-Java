package alessiovulpinari;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1-w3-d5-Java");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
