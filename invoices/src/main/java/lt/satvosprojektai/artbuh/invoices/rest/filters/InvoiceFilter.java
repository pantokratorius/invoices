package lt.satvosprojektai.artbuh.invoices.rest.filters;

import lombok.Getter;
import lombok.Setter;
import java.time.YearMonth;

@Getter
@Setter
public class InvoiceFilter {

    private String month;
    private String search;

    public YearMonth getParsedMonth() {
        return month == null ? null : YearMonth.parse(month);
    }
}