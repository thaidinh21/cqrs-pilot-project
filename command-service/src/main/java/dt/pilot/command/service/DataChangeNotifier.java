package dt.pilot.command.service;

import dt.pilot.shared.enums.RedisTopicEnum;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;

public interface DataChangeNotifier {
  void notify(InternalEventServicePayload payload, RedisTopicEnum topic);
}
