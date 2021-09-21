package com.bank.shared.infrastructure;

import com.bank.shared.domain.Service;
import com.bank.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
