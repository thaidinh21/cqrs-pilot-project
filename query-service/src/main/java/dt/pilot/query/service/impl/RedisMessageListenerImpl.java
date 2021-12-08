package dt.pilot.query.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import dt.pilot.shared.enums.RedisTopicEnum;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;
import dt.pilot.shared.pojo.dto.TestEntityDTO;
import dt.pilot.shared.service.SharedCommandService;
import lombok.extern.slf4j.Slf4j;

@Service("redisMessageListener")
@Slf4j
public class RedisMessageListenerImpl implements MessageListener {

  @Autowired
  private SharedCommandService service;

  private RedisSerializer<InternalEventServicePayload> serializer =
      new Jackson2JsonRedisSerializer<InternalEventServicePayload>((InternalEventServicePayload.class));

  private RedisSerializer<String> stringSerializer = RedisSerializer.string();
  
  private ObjectMapper mapper = new ObjectMapper();


  @Override
  public void onMessage(Message message, byte[] pattern) {
    Object convertedMessage = extractMessage(message);
    String convertedChannel = stringSerializer.deserialize(pattern);
    RedisTopicEnum topic = RedisTopicEnum.fromText(convertedChannel);
    log.debug("got message through redis with channel {}", convertedChannel);
    InternalEventServicePayload payload = (InternalEventServicePayload) convertedMessage;
    switch (topic) {
      case ENTITY_CREATED:
        service.create(mapper.convertValue(payload.getPayload(), TestEntityDTO.class));
        break;
      case ENTITY_UPDATED:
        service.update(mapper.convertValue(payload.getPayload(), TestEntityDTO.class));
        break;
      case ENTITY_DELETED:
        service.delete((String) payload.getPayload());
      default:
        break;
    }

  }

  protected Object extractMessage(Message message) {
    if (serializer != null) {
      return serializer.deserialize(message.getBody());
    }
    return message.getBody();
  }

}
