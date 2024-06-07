package alessiovulpinari.exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(String uuid) {
        System.out.println("Elemento: " + uuid + " non trovato!");
    }
}
