package com.dataox.datascraper.config;

import com.dataox.datascraper.config.sections.ProxyConfig;
import com.squareup.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;
import java.net.Proxy;


@Configuration
@RequiredArgsConstructor
public class OkHttpClientConfig {

    private final ProxyConfig proxyConfig;

    @Bean
    @Primary
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public OkHttpClient getProxyOkHttpClient() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress(proxyConfig.getHost(), proxyConfig.getPort()));
        OkHttpClient client = new OkHttpClient();
        client.setProxy(proxy);
        return client;
    }
}
