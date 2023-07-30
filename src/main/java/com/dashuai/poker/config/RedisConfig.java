package com.dashuai.poker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Customize Redis connection factory if needed (e.g., connection pool settings)
        // Set additional configuration properties if required
        return new LettuceConnectionFactory();
    }
}
