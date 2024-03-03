package com.example.usuarios.Utils.Autenticacion;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.*;

public class KeyGenerator {
    private static final KeyPair keyPair;

    static {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar el par de claves", e);
        }
    }

    public static Key getPublicKey() {
        return keyPair.getPublic();
    }

    public static Key getPrivateKey() {
        return keyPair.getPrivate();
    }
}
