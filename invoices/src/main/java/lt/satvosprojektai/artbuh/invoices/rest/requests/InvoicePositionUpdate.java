package lt.satvosprojektai.artbuh.invoices.rest.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InvoicePositionUpdate {

    @NotNull
    private BigDecimal price;

    private BigDecimal adjustmentAmount;
    private BigDecimal adjustmentPercent;
    private String notes;



}
