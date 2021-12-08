package dt.pilot.shared.service;

import dt.pilot.shared.entity.MongoTestEntity;
import dt.pilot.shared.pojo.dto.TestEntityDTO;

public interface SharedCommandService {
  MongoTestEntity create(TestEntityDTO body);

  MongoTestEntity update(TestEntityDTO body);

  String delete(String identity);
}
