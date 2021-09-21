package com.bank.customers.application;

import com.bank.customers.CustomersUnitTestCase;
import com.bank.customers.application.create.CustomerCreator;
import com.bank.customers.application.create.CustomerCreatorCommand;
import com.bank.customers.domain.CustomerExists;
import com.bank.customers.domain.CustomerMother;
import com.bank.shared.domain.UuidNotValid;
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
    void throw_an_exception_when_customer_is_null() {
        final Executable executable =
                () -> {
                    final var command = CustomerCreatorCommand.from(null);
                    creator.create(command);
                };
        assertThrows(NullPointerException.class, executable);
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
        assertThrows(UuidNotValid.class, executable);
    }

    @Test
    void throw_an_exception_when_customer_exists() {
        final Executable executable =
                () -> {
                    final var customer = CustomerMother.random();
                    shouldThrowCustomerExists(customer);
                    final var command = CustomerCreatorCommand.from(customer);
                    creator.create(command);
                };
        assertThrows(CustomerExists.class, executable);
    }
}
