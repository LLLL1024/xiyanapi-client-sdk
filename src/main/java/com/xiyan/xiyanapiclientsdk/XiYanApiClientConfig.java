package com.xiyan.xiyanapiclientsdk;

import com.xiyan.xiyanapiclientsdk.client.XiYanApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xiyanapi.client")
@Data
@ComponentScan
public class XiYanApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public XiYanApiClient xiYanApiClient(){
        return new XiYanApiClient(accessKey, secretKey);
    }
}
