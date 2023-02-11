package com.company.bcpayments;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

//import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@ComponentScan({"com.company.bcpayments"})
@EnableTransactionManagement

@EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.class })

public class Main {

    @Value("${:http://localhost:8545}")
    private String blockchaininternalhost;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
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