package dt.pilot.shared.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dt.pilot.shared.entity.MongoTestEntity;
import dt.pilot.shared.exception.BadRequestException;
import dt.pilot.shared.helper.EntityConvertor;
import dt.pilot.shared.pojo.dto.TestEntityDTO;
import dt.pilot.shared.repo.TestEntityRepo;
import dt.pilot.shared.service.SharedCommandService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SharedCommandServiceImpl implements SharedCommandService {
  private final TestEntityRepo repo;

  @Autowired
  public SharedCommandServiceImpl(final TestEntityRepo repo) {
    this.repo = repo;
  }

  @Override
  public MongoTestEntity create(TestEntityDTO body) {
    log.info("start creating new entity in shared");
    MongoTestEntity entity = EntityConvertor.dtoToEntity(body);
    entity = repo.save(entity);
    return entity;
  }

  @Override
  public MongoTestEntity update(TestEntityDTO body) {
    log.info("start updating entity in shared");
    if (null == body.getId()) {
      log.error("Bad request, id is required for this operation");
      throw new BadRequestException("ID is required for this operation");
    }

    MongoTestEntity entity = repo.findById(body.getId())
        .orElseThrow(() -> new BadRequestException("entity not existed"));
    entity.setName(body.getName());
    entity = repo.save(entity);
    return entity;
  }

  @Override
  public String delete(String identity) {
    log.info("start removing entity in shared");
    repo.deleteById(identity);
    return identity;
  }

}
