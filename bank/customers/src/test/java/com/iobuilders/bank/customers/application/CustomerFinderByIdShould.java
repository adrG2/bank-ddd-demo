package com.iobuilders.bank.customers.application;

import com.iobuilders.bank.customers.CustomersUnitTestCase;
import com.iobuilders.bank.customers.application.find.CustomerFinderById;
import com.iobuilders.bank.customers.domain.CustomerMother;
import com.iobuilders.bank.shared.domain.UuidMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CustomerFinderByIdShould extends CustomersUnitTestCase {
    private CustomerFinderById finder;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        finder = new CustomerFinderById(repository);
    }

    @Test
    void find_a_valid_customer() {
        final var id = UuidMother.random();
        final var customer = CustomerMother.random();
        shouldFind(id, customer);
        final var customerFound = finder.find(id);
        Assert.notNull(customerFound, "Customer not null");
    }

    @Test
    void throw_an_exception_when_repository_is_down() {
        final Executable executable = () -> shouldNotFound(UuidMother.random());
        assertThrows(Exception.class, executable);
    }
}
