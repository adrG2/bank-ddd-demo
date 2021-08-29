package com.iobuilders.bank.customers;

import com.iobuilders.bank.customers.domain.Customer;
import com.iobuilders.bank.customers.domain.CustomerExists;
import com.iobuilders.bank.customers.domain.CustomerRepository;
import com.iobuilders.bank.shared.domain.BankPasswordEncoder;
import com.iobuilders.bank.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class CustomersUnitTestCase extends UnitTestCase {
    protected CustomerRepository repository;
    protected BankPasswordEncoder bankPasswordEncoder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(CustomerRepository.class);
        bankPasswordEncoder = mock(BankPasswordEncoder.class);
    }

    public void shouldHaveSaved(Customer customer) {
        Mockito.doNothing().when(repository).save(customer);
    }

    public void shouldNotHaveSaved(Customer customer) {
        Mockito.doThrow(new Exception()).when(repository).save(customer);
    }

    public void shouldThrowCustomerExists(Customer customer) {
        Mockito.doThrow(new CustomerExists(customer.id().value()))
                .when(repository)
                .save(Mockito.any(Customer.class));
    }

    public void shouldFind(String id, Customer customer) {
        Mockito.when(repository.findOrFailById(id)).thenReturn(customer);
    }

    public void shouldNotFound(String id) {
        Mockito.doThrow(new Exception()).when(repository).findOrFailById(id);
    }

    public void shouldEncode(String text, String encoded) {
        Mockito.when(bankPasswordEncoder.encode(text)).thenReturn(encoded);
    }

    public void shouldNotEncode(String text) {
        Mockito.doThrow(new Exception()).when(bankPasswordEncoder).encode(text);
    }

    public void shouldMatch(String text, String otherText, boolean result) {
        Mockito.when(bankPasswordEncoder.matches(text, otherText)).thenReturn(result);
    }

    public void shouldNotMatches(String text, String hash) {
        Mockito.doThrow(new Exception()).when(bankPasswordEncoder).matches(text, hash);
    }
}
