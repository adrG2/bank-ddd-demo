package com.bank.customers.application;

import com.bank.customers.CustomersUnitTestCase;
import com.bank.customers.application.find.CustomerFinderById;
import com.bank.customers.domain.CustomerMother;
import com.bank.customers.domain.CustomerNotFound;
import com.bank.shared.domain.UuidMother;
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
        final Executable executable =
                () -> {
                    shouldNotFoundRepositoryDown(UuidMother.random());
                    finder.find(UuidMother.random());
                };
        assertThrows(Exception.class, executable);
    }

    @Test
    void throw_an_exception_when_customer_not_found() {
        final Executable executable =
                () -> {
                    shouldNotFound();
                    finder.find(UuidMother.random());
                };
        assertThrows(CustomerNotFound.class, executable);
    }
}
