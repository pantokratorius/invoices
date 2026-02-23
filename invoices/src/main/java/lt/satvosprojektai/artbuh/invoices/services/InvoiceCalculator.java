package lt.satvosprojektai.artbuh.invoices.service;

import lt.satvosprojektai.artbuh.invoices.dao.InvoicePosition;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class InvoiceCalculator {

    public BigDecimal calculatePositionFinal(InvoicePosition position) {

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
}