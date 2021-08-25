package com.iobuilders.bank.shared.infrastructure;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
