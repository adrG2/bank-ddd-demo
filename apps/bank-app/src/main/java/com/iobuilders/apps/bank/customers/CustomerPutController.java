package com.iobuilders.apps.bank.customers;

import com.iobuilders.bank.customers.application.create.CustomerCreator;
import com.iobuilders.bank.customers.application.create.CustomerCreatorCommand;
import com.iobuilders.bank.shared.domain.UuidGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CustomerPutController {

    private final CustomerCreator creator;
    private final UuidGenerator uuidGenerator;

    public CustomerPutController(CustomerCreator creator, UuidGenerator uuidGenerator) {
        this.creator = creator;
        this.uuidGenerator = uuidGenerator;
    }

    @PutMapping("/customers")
    public ResponseEntity<String> put(@RequestBody PostCustomerBody body) {
        final var id = uuidGenerator.generate();
        final var command =
                CustomerCreatorCommand.create(id, body.email, body.userName, body.password);
        creator.create(command);
        final var responseBody = String.format("Customer with id %s created", id);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    private static class PostCustomerBody {
        private final String userName;
        private final String email;
        private final String password;

        public PostCustomerBody(String userName, String email, String password) {
            this.userName = userName;
            this.email = email;
            this.password = password;
        }
    }
}
