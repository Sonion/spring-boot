package api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "db")
public class DBProperties {

    private String url;
    private String username;
    private String password;

    private int initialSize = 50;
    private int maxActive = 200;
    private int maxWait = 3000;
    private int timeBetweenEvictionRunsMillis = 60000;
    private int minEvictableIdleTimeMillis = 3600000;
    private int maxPoolPreparedStatementPerConnectionSize = 20;
}
