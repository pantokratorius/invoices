package lt.satvosprojektai.artbuh.invoices.rest.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceDto {

    private String id;
    private String clientName;
    private String companyCode;
    private String clientAddress;
    private String month;

    private BigDecimal totalBase;
    private BigDecimal totalPrice;
    private BigDecimal totalFinal;
}