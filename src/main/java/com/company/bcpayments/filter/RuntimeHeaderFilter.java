package com.company.bcpayments.filter;
//package com.company.bcpayments.filter;
//
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Adds a header to all network responses to indicate the current runtime.
// * Setter only required for testing!
// */
//@Setter
//@Component
//@WebFilter("/api/v1/**")
//public class RuntimeHeaderFilter implements Filter {
//
//    @Value("${api.runtime}")
//    private String runtime;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("X-api-Runtime", runtime);
//        chain.doFilter(request, response);
//    }
//}
