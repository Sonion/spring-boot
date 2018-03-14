package api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformSearhClientAutoConfigure {

    @Bean
    SecurityDepositClient securityDepositClient(){
        return new SecurityDepositClient();
    }
}
