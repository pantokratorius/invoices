package lt.satvosprojektai.artbuh.invoices.specifications;

import lt.satvosprojektai.artbuh.invoices.dao.Invoice;
import org.springframework.data.jpa.domain.Specification;

import java.time.YearMonth;

public class InvoiceSpecifications {

    public static Specification<Invoice> byMonth(YearMonth month) {
        return (root, query, cb) ->
                month == null
                        ? null
                        : cb.equal(root.get("invoiceMonth"), month);
    }

    public static Specification<Invoice> bySearch(String search) {
        return (root, query, cb) -> {

            if (search == null || search.isBlank()) {
                return null;
            }

            String like = "%" + search.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("clientName")), like),
                    cb.like(cb.lower(root.get("companyCode")), like)
            );
        };
    }
}