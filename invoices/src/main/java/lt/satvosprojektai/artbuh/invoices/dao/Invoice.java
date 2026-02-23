package lt.satvosprojektai.artbuh.invoices.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    private String clientName;
    private String companyCode;
    private String clientAddress;

    @Column(name = "invoice_month")
    private String invoiceMonth;


    @OneToMany(mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<InvoicePosition> positions = new ArrayList<>();

    public BigDecimal getTotalBase() {
        return positions.stream()
                .map(InvoicePosition::getBaseAmount)
                .filter(java.util.Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPrice() {
        return positions.stream()
                .map(InvoicePosition::getPrice)
                .filter(java.util.Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalFinal() {
        return positions.stream()
                .map(InvoicePosition::getFinalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
