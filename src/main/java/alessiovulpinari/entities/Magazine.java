package alessiovulpinari.entities;

import alessiovulpinari.enums.Periodicity;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("rivista")
public class Magazine extends CatalogueElement {

    @Column(name = "peridiocita", nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine() {
    }

    public Magazine(String title, int yearOfPublication, int pages, Periodicity periodicity) {
        super(title, yearOfPublication, pages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", dateOfPublication=" + yearOfPublication +
                ", pages=" + pages +
                ", periodicity=" + periodicity +
                '}';
    }
}
