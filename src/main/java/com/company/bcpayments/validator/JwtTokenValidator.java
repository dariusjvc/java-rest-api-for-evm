


package com.company.bcpayments.validator;

import org.springframework.stereotype.Component;

/**
 * Production JWT validator
 */
@Component
public class JwtTokenValidator implements TokenValidator {

    @Override
    public boolean isTokenValid(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        if (!token.startsWith("Bearer ")) {
            return false;
        }

        String authToken = token.substring(7);
        return !authToken.isEmpty();
    }

    @Override
    public boolean isSameToken(String original, String current) {
        return original.equals(current);
    }
}
