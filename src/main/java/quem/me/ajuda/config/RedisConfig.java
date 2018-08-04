package quem.me.ajuda.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@Profile(RedisConfig.PROFILE)
public class RedisConfig {
	static final String PROFILE = "prod";
	private static final String REDIS_URL = "REDIS_URL";

	@Bean
	JedisConnectionFactory jedisConnectionFactory() throws URISyntaxException {
		String redistogoUrl = System.getenv(REDIS_URL);
		URI redistogoUri = new URI(redistogoUrl);

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				redistogoUri.getHost(), redistogoUri.getPort());
		
		RedisPassword password = RedisPassword.of(redistogoUri.getUserInfo().split(":", 2)[1]);
		redisStandaloneConfiguration.setPassword(password);

		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() throws URISyntaxException {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
