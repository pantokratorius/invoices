package lt.satvosprojektai.artbuh.invoices.rest.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InvoicePositionCreate {

    @NotNull
    private String classifierCode;

    @NotNull
    private String classifierName;

    @NotNull
    private BigDecimal price;

    private BigDecimal adjustmentAmount;
    private BigDecimal adjustmentPercent;
    private String notes;

}
