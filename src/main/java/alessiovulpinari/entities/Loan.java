package alessiovulpinari.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestiti")
public class Loan {

    @Id
    @GeneratedValue
    private UUID loanId;

    @ManyToOne
    @JoinColumn(name = "codice_carta", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "isbn", nullable = false)
    private CatalogueElement catalogueElement;

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate loanStartDate;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate expLoanRepaymentDate;

    @Column(name = "data_restituzione_effettiva", nullable = false)
    private LocalDate actLoanRepaymentDate;

    public Loan() {
    }

    public Loan(LocalDate loanStartDate, LocalDate expLoanRepaymentDate, LocalDate actLoanRepaymentDate) {
        this.loanStartDate = loanStartDate;
        this.expLoanRepaymentDate = expLoanRepaymentDate;
        this.actLoanRepaymentDate = actLoanRepaymentDate;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CatalogueElement getCatalogueElement() {
        return catalogueElement;
    }

    public void setCatalogueElement(CatalogueElement catalogueElement) {
        this.catalogueElement = catalogueElement;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getExpLoanRepaymentDate() {
        return expLoanRepaymentDate;
    }

    public void setExpLoanRepaymentDate(LocalDate expLoanRepaymentDate) {
        this.expLoanRepaymentDate = expLoanRepaymentDate;
    }

    public LocalDate getActLoanRepaymentDate() {
        return actLoanRepaymentDate;
    }

    public void setActLoanRepaymentDate(LocalDate actLoanRepaymentDate) {
        this.actLoanRepaymentDate = actLoanRepaymentDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanStartDate=" + loanStartDate +
                ", expLoanRepaymentDate=" + expLoanRepaymentDate +
                ", actLoanRepaymentDate=" + actLoanRepaymentDate +
                '}';
    }
}
