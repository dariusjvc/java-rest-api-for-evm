

package com.company.bcpayments.validator;

/**
 *
 */
public interface TokenValidator {
    /**
     * Check if the given token is valid or not.
     *
     * @param token Token to check validity
     * @return True if token is valid, false otherwise
     */
    boolean isTokenValid(String token);

    /**
     * Check if the given tokens are equals
     *
     * @param original Original token
     * @param current  Saved token
     * @return True is the two tokens are equals, false otherwise
     */
    boolean isSameToken(String original, String current);
}
