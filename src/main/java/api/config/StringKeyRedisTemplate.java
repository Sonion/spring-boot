package api.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by wujinliang on 2018/3/12.
 */
public class StringKeyRedisTemplate extends RedisTemplate<String, Object> {

    /**
     * Constructs a new <code>StringKeyRedisTemplate</code> instance. {@link #setConnectionFactory(RedisConnectionFactory)}
     * and {@link #afterPropertiesSet()} still need to be called.
     */
    public StringKeyRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        setKeySerializer(stringSerializer);
        setHashKeySerializer(stringSerializer);
    }

    @Override
    public HashOperations<String, String, Object> opsForHash() {
        return super.opsForHash();
    }

    @Override
    public BoundHashOperations<String, String, Object> boundHashOps(String key) {
        return super.boundHashOps(key);
    }

    /**
     * Constructs a new <code>StringKeyRedisTemplate</code> instance ready to be used.
     *
     * @param connectionFactory connection factory for creating new connections
     */
    public StringKeyRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }
}

