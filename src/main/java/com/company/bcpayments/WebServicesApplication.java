

package com.company.bcpayments;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@EnableRetry
@EnableScheduling
@Slf4j
@SpringBootApplication
public class WebServicesApplication {
    /**
     * @param args standard args
     */
    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(WebServicesApplication.class);
        app.run(args);
    }
    @Value("${:http://localhost:8545}")
    private String blockchaininternalhost;
    /**
     * Creates a {@link RestTemplate} that allows auto signed certificates.
     *
     * @return RestTemplate template object
     * @throws KeyStoreException        When loading crypto material
     * @throws NoSuchAlgorithmException When loading crypto material
     * @throws KeyManagementException   When loading crypto material
     */
    @Bean
    public RestTemplate getRestTemplate(Environment env) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setReadTimeout(env.getProperty("oauth.timeout", Integer.class, 60000));
        requestFactory.setConnectionRequestTimeout(env.getProperty("oauth.timeout", Integer.class, 60000));

        return new RestTemplate(requestFactory);
    }

    @Bean
    public Web3j createEthereumClient(Environment environment) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .build();

        HttpService httpService = new HttpService(blockchaininternalhost, client, true);

        return Web3j.build(httpService);
    }
}
