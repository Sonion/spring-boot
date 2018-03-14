package api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * 用于缓存redis配置
 * Created by wujinliang on 2018/3/8.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheRedisConfiguration {

    private String master;
    private String sentinel;
    private String password;

    @Bean
    public RedisSentinelConfiguration cacheRedisSentinelConfiguration() {
        Set<String> sentinelHostAndPorts = StringUtils.commaDelimitedListToSet(sentinel);
        return new RedisSentinelConfiguration(master, sentinelHostAndPorts);
    }

    @Bean
    public JedisPoolConfig cacheJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(20);
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        poolConfig.setMinEvictableIdleTimeMillis(30000);
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }

    @Bean
    public JedisConnectionFactory cacheJedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory(cacheRedisSentinelConfiguration());
        factory.setPassword(password);
        factory.setPoolConfig(cacheJedisPoolConfig());
        factory.setTimeout(15000);
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    public RedisTemplate cacheRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(cacheJedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate cacheStringRedisTemplate() {
        return new StringRedisTemplate(cacheJedisConnectionFactory());
    }

    @Bean
    public StringKeyRedisTemplate cacheStringKeyRedisTemplate() {
        return new StringKeyRedisTemplate(cacheJedisConnectionFactory());
    }
}

