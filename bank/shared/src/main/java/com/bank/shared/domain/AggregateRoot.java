package com.bank.shared.domain;

import com.bank.shared.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public final List<DomainEvent> pullEvents() {
        final var events = this.domainEvents;
        this.domainEvents = Collections.emptyList();
        return events;
    }

    protected final void pushEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }
}
