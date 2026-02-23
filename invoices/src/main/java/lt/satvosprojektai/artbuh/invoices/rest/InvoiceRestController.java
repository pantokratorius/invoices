package lt.satvosprojektai.artbuh.invoices.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lt.satvosprojektai.artbuh.invoices.mappers.InvoiceMapper;
import lt.satvosprojektai.artbuh.invoices.rest.requests.*;
import lt.satvosprojektai.artbuh.invoices.rest.responses.*;
import lt.satvosprojektai.artbuh.invoices.services.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceRestController {

    private final InvoiceService service;
    private final InvoiceMapper mapper;

    @GetMapping
    public Page<InvoiceDto> getInvoices(Pageable pageable) {
        return service.getInvoices(pageable)
                .map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public InvoiceDto getInvoice(@PathVariable String id) {
        return mapper.toDto(service.getInvoice(id));
    }

    @PostMapping("/{id}/positions")
    public InvoicePositionDto addPosition(
            @PathVariable String id,
            @Valid @RequestBody InvoicePositionCreate request) {

        return mapper.toDto(service.addPosition(id, request));
    }

    @PostMapping("/search")
    public Page<InvoiceDto> searchInvoices(
            @RequestBody InvoiceFilter filter,
            Pageable pageable
    ) {
        return invoiceService.getInvoices(filter, pageable)
                .map(invoiceMapper::toDto);
    }

    @PutMapping("/positions/{positionId}")
    public InvoicePositionDto updatePosition(
            @PathVariable String positionId,
            @Valid @RequestBody InvoicePositionUpdate request) {

        return mapper.toDto(service.updatePosition(positionId, request));
    }

    @DeleteMapping("/positions/{positionId}")
    public void deletePosition(@PathVariable String positionId) {
        service.deletePosition(positionId);
    }

}
