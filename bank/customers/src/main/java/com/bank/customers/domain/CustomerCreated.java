package com.bank.customers.domain;

import com.bank.shared.domain.bus.event.DomainEvent;

public final class CustomerCreated extends DomainEvent {

    public CustomerCreated(String aggregateId) {
        super(aggregateId);
    }

    @Override
    public String eventName() {
        return "customer.created";
    }
}
