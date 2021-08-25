package com.iobuilders.bank.shared.infrastructure;

import com.iobuilders.bank.shared.domain.BankPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptBankPasswordEncoder implements BankPasswordEncoder {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String text) {
        return encoder.encode(text);
    }

    @Override
    public Boolean matches(String text, String hash) {
        return encoder.matches(text, hash);
    }
}
