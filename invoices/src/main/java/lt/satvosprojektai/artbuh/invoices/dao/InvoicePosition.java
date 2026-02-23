import java.math.RoundingMode;

@Getter
@Setter
@Entity
@Table(name = "invoice_positions")
public class InvoicePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    private String classifierCode;
    private String classifierName;

    @Column(precision = 19, scale = 2)
    private BigDecimal baseAmount;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @Column(precision = 19, scale = 2)
    private BigDecimal adjustmentAmount;

    @Column(precision = 5, scale = 2)
    private BigDecimal adjustmentPercent;

    private String notes;

    public BigDecimal getFinalAmount() {

        BigDecimal result = price != null ? price : BigDecimal.ZERO;

        if (adjustmentAmount != null) {
            result = result.add(adjustmentAmount);
        }

        if (adjustmentPercent != null && price != null) {
            BigDecimal percent = price
                    .multiply(adjustmentPercent)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            result = result.add(percent);
        }

        return result;
    }
}