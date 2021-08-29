package com.iobuilders.bank.customers.application;

import com.iobuilders.bank.customers.CustomersUnitTestCase;
import com.iobuilders.bank.customers.application.create.CustomerCreator;
import com.iobuilders.bank.customers.application.create.CustomerCreatorCommand;
import com.iobuilders.bank.customers.domain.CustomerExists;
import com.iobuilders.bank.customers.domain.CustomerMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CustomerCreatorShould extends CustomersUnitTestCase {
    private CustomerCreator creator;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        creator = new CustomerCreator(eventBus, repository, bankPasswordEncoder);
    }

    @Test
    void create_valid_customer() {
        final var customer = CustomerMother.random();
        shouldHaveSaved(customer);
        final var uuid = UUID.randomUUID().toString();
        shouldGenerateUuid(uuid);
        final var command = CustomerCreatorCommand.from(customer);
        creator.create(command);
    }

    @Test
    void throw_an_exception_when_repository_is_down() {
        final Executable executable =
                () -> {
                    final var customer = CustomerMother.random();
                    shouldNotHaveSaved(customer);
                    final var command = CustomerCreatorCommand.from(customer);
                    creator.create(command);
                };
        assertThrows(Exception.class, executable);
    }

    @Test
    void throw_an_exception_when_customer_exists() {
        final Executable executable =
                () -> {
                    final var customer = CustomerMother.random();
                    shouldCustomerExists(customer);
                    final var command = CustomerCreatorCommand.from(customer);
                    creator.create(command);
                };
        assertThrows(CustomerExists.class, executable);
    }

    @Test
    void throw_an_exception_when_customer_is_null() {
        final Executable executable =
                () -> {
                    final var command = CustomerCreatorCommand.from(null);
                    creator.create(command);
                };
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void throw_an_exception_when_customer_id_is_null() {
        final Executable executable =
                () -> {
                    final var customer = CustomerMother.withIdNull();
                    final var command = CustomerCreatorCommand.from(customer);
                    creator.create(command);
                };
        assertThrows(NullPointerException.class, executable);
    }

    @Test
    void throw_an_exception_when_customer_id_not_valid_uuid() {
        final Executable executable =
                () -> {
                    final var customer = CustomerMother.withIdNotValid();
                    final var command = CustomerCreatorCommand.from(customer);
                    creator.create(command);
                };
        assertThrows(IllegalArgumentException.class, executable);
    }
}
