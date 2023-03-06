

package com.company.bcpayments.filter;

import com.company.bcpayments.auth.TokenAuthoritiesExtractor;
import com.company.bcpayments.service.AuthService;
import com.company.bcpayments.validator.TokenValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final TokenValidator tokenValidator;
    private final TokenAuthoritiesExtractor tokenAuthoritiesExtractor;

    @Value("${api.auth.check.authorization}")
    private String[] authorizedRoles;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("Prepared to check if request from {} contains JWT token", request.getRemoteHost());
        String authorization = request.getHeader("Authorization");

        if (!tokenValidator.isTokenValid(authorization)) {
            log.error("Authorization header is not valid.");
            sendError(request, response);
            return;
        }

        // Check Authorization Role
        String[] authorities = tokenAuthoritiesExtractor.authorities(authorization);

        if (!testAuthorizationToken(authorities)) {
            log.error("Received Roles are not in the list of allowed roles.");
            sendError(request, response);
            return;
        }
        // Check if tokens match
        String authToken = authorization.substring(7);

        try {
            if (!authService.checkToken(authToken)) {
                log.warn("Authorization header don't match saved token.");
                sendError(request, response);
                return;
            }
        } catch (Exception e) {
            log.error("Error checking token.");
            sendError(request, response);
            return;
        }

        // If token is valid, then forward the request
        log.debug("Request authorized.");
        filterChain.doFilter(request, response);
    }

    /**
     * @param authorities Token authorities
     * @return True if authorization role is allowed, false otherwise
     */
    private boolean testAuthorizationToken(String[] authorities) {
        if (authorities == null || authorities.length == 0) return false;

        for (String authority : authorities) {
            if (Arrays.asList(authorizedRoles).contains(authority)) {
                log.debug("Role is in the authorization list.");
                return true;
            }
        }
        return false;
    }

    /**
     * @param request  Current request
     * @param response Response to retrieve to client
     * @throws IOException If an unexpected error occurs
     */
    private void sendError(ServletRequest request, HttpServletResponse response) throws IOException {
        log.error("Authorization needed to access this resource: Remote host {}({})",
                request.getRemoteHost(), request.getRemoteAddr());
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization needed to access this resource");
    }
}
