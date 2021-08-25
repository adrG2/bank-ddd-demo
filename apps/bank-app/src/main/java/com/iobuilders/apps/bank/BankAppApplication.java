package com.iobuilders.apps.bank;

import com.iobuilders.bank.shared.domain.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        includeFilters =
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {
            "com.iobuilders.bank.shared",
            "com.iobuilders.bank.customers",
            "com.iobuilders.apps.bank"
        })
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }
}
