package dt.pilot.command.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;

@Configuration
public class RedisTemplateConfiguration {

  @Bean
  @Primary
  public RedisTemplate<String, InternalEventServicePayload> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {

    RedisTemplate<String, InternalEventServicePayload> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setDefaultSerializer(new Jackson2JsonRedisSerializer<InternalEventServicePayload>(InternalEventServicePayload.class));
    template.afterPropertiesSet();
    return template;
  }

}
