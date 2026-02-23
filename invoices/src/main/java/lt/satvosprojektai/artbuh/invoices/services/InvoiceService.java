package lt.satvosprojektai.artbuh.invoices.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import lt.satvosprojektai.artbuh.invoices.dao.*;
import lt.satvosprojektai.artbuh.invoices.repositories.*;
import lt.satvosprojektai.artbuh.invoices.rest.requests.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoicePositionRepository positionRepository;

    public Page<Invoice> getInvoices(InvoiceFilter filter, Pageable pageable) {

        YearMonth month = null;
        if (filter != null && filter.getMonth() != null && !filter.getMonth().isBlank()) {
            month = filter.getParsedMonth();
        }

        String search = filter != null ? filter.getSearch() : null;

        Specification<Invoice> spec = Specification
                .where(InvoiceSpecifications.byMonth(month))
                .and(InvoiceSpecifications.bySearch(search));

        return invoiceRepository.findAll(spec, pageable);
    }

    public Invoice getInvoice(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Transactional
    public InvoicePosition addPosition(String invoiceId,
                                       InvoicePositionCreate request) {

        Invoice invoice = getInvoice(invoiceId);

        InvoicePosition position = new InvoicePosition();
        position.setInvoice(invoice);
        position.setClassifierCode(request.getClassifierCode());
        position.setClassifierName(request.getClassifierName());
        position.setBaseAmount(request.getBaseAmount());
        position.setPrice(request.getPrice());
        position.setAdjustmentAmount(request.getAdjustmentAmount());
        position.setAdjustmentPercent(request.getAdjustmentPercent());
        position.setNotes(request.getNotes());

        InvoicePosition saved = positionRepository.save(position);

        recalculateInvoiceTotals(invoice);

        return saved;
    }

    @Transactional
    public InvoicePosition updatePosition(String positionId,
                                          InvoicePositionUpdate request) {

        InvoicePosition position =
                positionRepository.findById(positionId)
                        .orElseThrow(() -> new RuntimeException("Position not found"));

        position.setPrice(request.getPrice());
        position.setAdjustmentAmount(request.getAdjustmentAmount());
        position.setAdjustmentPercent(request.getAdjustmentPercent());
        position.setNotes(request.getNotes());

        InvoicePosition saved = positionRepository.save(position);

        recalculateInvoiceTotals(position.getInvoice());

        return saved;
    }

    @Transactional
    public void deletePosition(String positionId) {

        InvoicePosition position =
                positionRepository.findById(positionId)
                        .orElseThrow(() -> new RuntimeException("Position not found"));

        Invoice invoice = position.getInvoice();

        positionRepository.delete(position);

        recalculateInvoiceTotals(invoice);
    }

    private BigDecimal calculateFinalAmount(InvoicePosition position) {

        BigDecimal price = position.getPrice() != null
                ? position.getPrice()
                : BigDecimal.ZERO;

        BigDecimal result = price;

        if (position.getAdjustmentAmount() != null) {
            result = result.add(position.getAdjustmentAmount());
        }

        if (position.getAdjustmentPercent() != null) {
            BigDecimal percent = price
                    .multiply(position.getAdjustmentPercent())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            result = result.add(percent);
        }

        return result;
    }

    private void recalculateInvoiceTotals(Invoice invoice) {

        BigDecimal totalBase = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal totalFinal = BigDecimal.ZERO;

        for (InvoicePosition position : invoice.getPositions()) {

            if (position.getBaseAmount() != null)
                totalBase = totalBase.add(position.getBaseAmount());

            if (position.getPrice() != null)
                totalPrice = totalPrice.add(position.getPrice());

            totalFinal = totalFinal.add(calculateFinalAmount(position));
        }

        invoice.setTotalBase(totalBase);
        invoice.setTotalPrice(totalPrice);
        invoice.setTotalFinal(totalFinal);
    }



}