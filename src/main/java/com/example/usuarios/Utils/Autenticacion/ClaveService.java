package com.example.usuarios.Utils.Autenticacion;

import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class ClaveService {
    private final Key pubblicKey;

    public ClaveService() {
        this.pubblicKey = KeyGenerator.getPublicKey();
    }

    public Key getPublicKey() {
        return this.pubblicKey;
    }
}
