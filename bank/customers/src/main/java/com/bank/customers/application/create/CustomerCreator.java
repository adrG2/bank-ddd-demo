package com.bank.customers.application.create;

import com.bank.customers.domain.Customer;
import com.bank.customers.domain.CustomerRepository;
import com.bank.shared.domain.BankPasswordEncoder;
import com.bank.shared.domain.bus.event.EventBus;
import org.springframework.stereotype.Service;

@Service
public final class CustomerCreator {

    private final EventBus eventBus;
    private final CustomerRepository repository;
    private final BankPasswordEncoder encoder;

    public CustomerCreator(
            EventBus eventBus, CustomerRepository repository, BankPasswordEncoder encoder) {
        this.eventBus = eventBus;
        this.repository = repository;
        this.encoder = encoder;
    }

    public void create(CustomerCreatorCommand command) {
        final var id = command.getId();
        final var passwordEncoded = encoder.encode(command.getPassword());
        final var email = command.getEmail();
        final var username = command.getUserName();
        final var customer = Customer.create(id, passwordEncoded, email, username);
        repository.save(customer);
        eventBus.publish(customer.pullEvents());
    }
}
