package lt.satvosprojektai.artbuh.invoices.rest.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvoicePositionDto {

    private String id;
    private String classifierCode;
    private String classifierName;
    private BigDecimal baseAmount;
    private BigDecimal price;
    private BigDecimal adjustmentAmount;
    private BigDecimal adjustmentPercent;
    private BigDecimal finalAmount;
    private String notes;
}