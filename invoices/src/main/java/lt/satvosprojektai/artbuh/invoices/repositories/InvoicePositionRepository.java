package lt.satvosprojektai.artbuh.invoices.repositories;

import lt.satvosprojektai.artbuh.invoices.dao.InvoicePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicePositionRepository
        extends JpaRepository<InvoicePosition, String> {
}
