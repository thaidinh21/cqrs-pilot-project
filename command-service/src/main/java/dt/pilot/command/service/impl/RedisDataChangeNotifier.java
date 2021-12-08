package dt.pilot.command.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import dt.pilot.command.service.DataChangeNotifier;
import dt.pilot.shared.enums.RedisTopicEnum;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisDataChangeNotifier implements DataChangeNotifier {
  private final RedisTemplate<String, InternalEventServicePayload> template;

  @Autowired
  public RedisDataChangeNotifier(RedisTemplate<String, InternalEventServicePayload> template) {
    this.template = template;
  }

  @Override
  @Async
  public void notify(InternalEventServicePayload payload, RedisTopicEnum topic) {
    log.debug("start publish message into topic", topic.toString());

    template.convertAndSend(topic.getTopic(), payload);
  }

}
