package org.swe.core.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BuyTicketDTO {
    @NotNull(message = "Event ID cannot be null.")
    private Integer eventId;

    @NotNull(message = "Quantity cannot be null.")
    @Min(value = 1, message = "Quantity must be greater than 0.")
    private Integer quantity;


    // TODO add payment method
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



}