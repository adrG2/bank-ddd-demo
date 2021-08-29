package com.iobuilders.apps.bank.customers;

import com.iobuilders.bank.customers.application.create.CustomerCreator;
import com.iobuilders.bank.customers.application.create.CustomerCreatorCommand;
import com.iobuilders.bank.customers.domain.CustomerExists;
import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.shared.domain.UuidNotValid;
import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CustomerPutController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerPutController.class);

    private final CustomerCreator creator;
    private final UuidGenerator uuidGenerator;

    public CustomerPutController(CustomerCreator creator, UuidGenerator uuidGenerator) {
        this.creator = creator;
        this.uuidGenerator = uuidGenerator;
    }

    @PutMapping("/customers")
    public ResponseEntity<String> put(@RequestBody PostCustomerBody body) {
        final var id = uuidGenerator.generate();
        ensureValidInputParameters(id, body);
        final var command =
                CustomerCreatorCommand.create(id, body.email, body.userName, body.password);
        try {
            creator.create(command);
        } catch (CustomerExists ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
        } catch (UuidNotValid | IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(
                String.format("Customer with id %s created", id), HttpStatus.CREATED);
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

    private void ensureValidInputParameters(String id, PostCustomerBody body) {
        GuardClauses.ensureStringIsNotBlank(id);
        GuardClauses.ensureStringIsNotBlank(body.userName);
        GuardClauses.ensureStringIsNotBlank(body.email);
        GuardClauses.ensureStringIsNotBlank(body.password);
    }
}
