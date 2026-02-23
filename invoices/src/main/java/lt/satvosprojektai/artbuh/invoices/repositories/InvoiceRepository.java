package lt.satvosprojektai.artbuh.invoices.repositories;

import lt.satvosprojektai.artbuh.invoices.dao.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceRepository extends
        JpaRepository<Invoice, String>,
        JpaSpecificationExecutor<Invoice> {
}
