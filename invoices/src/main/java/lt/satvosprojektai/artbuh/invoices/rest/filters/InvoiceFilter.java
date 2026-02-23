package lt.satvosprojektai.artbuh.invoices.rest.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceFilter {

    private String month;
    private String search;

    public YearMonth getParsedMonth() {
        return month == null ? null : YearMonth.parse(month);
    }
}