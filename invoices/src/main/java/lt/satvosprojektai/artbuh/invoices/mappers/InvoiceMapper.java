package lt.satvosprojektai.artbuh.invoices.mappers;

import lt.satvosprojektai.artbuh.invoices.dao.*;
import lt.satvosprojektai.artbuh.invoices.rest.responses.*;

import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceDto toDto(Invoice invoice) {

        InvoiceDto dto = new InvoiceDto();

        dto.setId(invoice.getId());
        dto.setClientName(invoice.getClientName());
        dto.setCompanyCode(invoice.getCompanyCode());
        dto.setClientAddress(invoice.getClientAddress());
        dto.setMonth(invoice.getInvoiceMonth());
        dto.setTotalBase(invoice.getTotalBase());
        dto.setTotalPrice(invoice.getTotalPrice());
        dto.setTotalFinal(invoice.getTotalFinal());

        return dto;
    }

    public InvoicePositionDto toDto(InvoicePosition position) {

        InvoicePositionDto dto = new InvoicePositionDto();

        dto.setId(position.getId());
        dto.setClassifierCode(position.getClassifierCode());
        dto.setClassifierName(position.getClassifierName());
        dto.setBaseAmount(position.getBaseAmount());
        dto.setPrice(position.getPrice());
        dto.setAdjustmentAmount(position.getAdjustmentAmount());
        dto.setAdjustmentPercent(position.getAdjustmentPercent());
        dto.setFinalAmount(position.getFinalAmount());
        dto.setNotes(position.getNotes());

        return dto;
    }
}
