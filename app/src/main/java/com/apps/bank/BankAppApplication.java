package com.apps.bank;

import com.bank.shared.domain.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        includeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"com.bank", "com.apps.bank"})
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }
}
