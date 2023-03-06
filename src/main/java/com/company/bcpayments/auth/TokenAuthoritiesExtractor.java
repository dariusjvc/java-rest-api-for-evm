

package com.company.bcpayments.auth;

public interface TokenAuthoritiesExtractor {
    String[] authorities(String token);
}
