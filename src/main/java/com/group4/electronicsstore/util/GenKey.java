package com.group4.electronicsstore.util;

import io.jsonwebtoken.security.Keys;
import java.util.Base64;

public class GenKey {
    public static void main(String[] args) {
        System.out.println(
            Base64.getEncoder().encodeToString(
                Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded()
            )
        );
    }
}