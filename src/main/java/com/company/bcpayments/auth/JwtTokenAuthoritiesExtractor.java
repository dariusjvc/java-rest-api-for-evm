

package com.company.bcpayments.auth;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 *
 */
@Slf4j
@Component
public class JwtTokenAuthoritiesExtractor implements TokenAuthoritiesExtractor {
    private final Gson gson;

    public JwtTokenAuthoritiesExtractor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String[] authorities(String token) {
        String bearer = token.replace("Bearer ", "").trim();

        String[] split = bearer.split("\\.");

        try {
            byte[] bytes = Base64.getDecoder().decode(split[1]);
            String json = new String(bytes);

            //Token obj = gson.fromJson(json, Token.class);
            //return obj.getAuthorities();

        } catch (Exception e) {
            log.error("Error parsing token.");
        }
        // return empty array of authorities
        return new String[0];
    }
}
