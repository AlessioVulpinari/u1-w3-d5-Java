package alessiovulpinari.exceptions;

import java.util.UUID;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(UUID uuid) {
        System.out.println("Elemento: " + uuid.toString() + " non trovato!");
    }
}
