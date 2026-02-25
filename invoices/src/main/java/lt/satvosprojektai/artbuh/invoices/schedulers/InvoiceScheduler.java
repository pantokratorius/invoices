package lt.satvosprojektai.artbuh.invoice.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lt.satvosprojektai.artbuh.invoice.services.InvoiceService;

@Service
public class InvoiceSheduler {

    private final InvoiceService invoiceService;

    public InvoiceSheduler(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Scheduled(cron = "0 0 0 1 * *", zone = "Europe/Vilnius")
    public void initCurrentPeriodIn() {

        invoiceService.initCurrentPeriodInvoices();

    }

}



public void generateMonthlyInvoices() {
    service.generateForCurrentMonth();
}