package dt.pilot.command.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dt.pilot.command.service.CommandService;
import dt.pilot.command.service.DataChangeNotifier;
import dt.pilot.shared.entity.MongoTestEntity;
import dt.pilot.shared.enums.RedisTopicEnum;
import dt.pilot.shared.helper.EntityConvertor;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;
import dt.pilot.shared.pojo.dto.TestEntityDTO;
import dt.pilot.shared.service.SharedCommandService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandServiceImpl implements CommandService {

  private final SharedCommandService testEntityCmdService;
  private final DataChangeNotifier dataChangeNotifier;

  @Autowired
  public CommandServiceImpl(final SharedCommandService testEntityCmdService,
      final DataChangeNotifier dataChangeNotifier) {
    this.testEntityCmdService = testEntityCmdService;
    this.dataChangeNotifier = dataChangeNotifier;
  }


  @Override
  public String create(TestEntityDTO body) {

    MongoTestEntity entity = testEntityCmdService.create(body);
    InternalEventServicePayload payload =
        EntityConvertor.entityToInternalPayload(EntityConvertor.entityToDto(entity));
    dataChangeNotifier.notify(payload, RedisTopicEnum.ENTITY_CREATED);
    return entity.getId();
  }

  @Override
  public String update(TestEntityDTO body) {
    log.info("start updating");
  
    MongoTestEntity entity = testEntityCmdService.update(body);
    InternalEventServicePayload payload =
        EntityConvertor.entityToInternalPayload(EntityConvertor.entityToDto(entity));
    dataChangeNotifier.notify(payload, RedisTopicEnum.ENTITY_UPDATED);
    return entity.getId();
  }

  @Override
  public String delete(String identity) {
    String rs = testEntityCmdService.delete(identity);
    dataChangeNotifier.notify(EntityConvertor.entityToInternalPayload(rs),
        RedisTopicEnum.ENTITY_DELETED);
    return identity;
  }

}
