package com.iobuilders.apps.bank.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public final class CustomerPostController {

    @PostMapping("/customers")
    public ResponseEntity<String> post(@RequestBody PostCustomerBody body) {
        final var id = UUID.randomUUID().toString();
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

        public String getUserName() {
            return userName;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}