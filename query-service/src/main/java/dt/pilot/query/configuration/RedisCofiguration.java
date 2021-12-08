package dt.pilot.query.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import dt.pilot.query.service.impl.RedisMessageListenerImpl;
import dt.pilot.shared.enums.RedisTopicEnum;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;

@Configuration
public class RedisCofiguration {
  @Autowired
  @Qualifier("redisMessageListener")
  private RedisMessageListenerImpl listener;

  @Bean
  RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

    container.setConnectionFactory(redisConnectionFactory);

    // container.addMessageListener();
    // ChannelTopic topic = new ChannelTopic("entity.created");
    List<Topic> topics = new ArrayList<>();
    topics.add(new ChannelTopic(RedisTopicEnum.ENTITY_CREATED.getTopic()));
    topics.add(new ChannelTopic(RedisTopicEnum.ENTITY_UPDATED.getTopic()));
    topics.add(new ChannelTopic(RedisTopicEnum.ENTITY_DELETED.getTopic()));
    container.addMessageListener(listener, topics);
//    MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(listener, "handleMessage");
//    messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer((InternalEventServicePayload.class)));
//    container.addMessageListener(lis, topics);
    return container;
  }
}
