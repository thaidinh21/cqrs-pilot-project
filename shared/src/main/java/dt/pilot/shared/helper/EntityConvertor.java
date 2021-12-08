package dt.pilot.shared.helper;

import dt.pilot.shared.entity.MongoTestEntity;
import dt.pilot.shared.pojo.dto.InternalEventServicePayload;
import dt.pilot.shared.pojo.dto.TestEntityDTO;

public final class EntityConvertor {
  private EntityConvertor() {}

  public static final MongoTestEntity dtoToEntity(TestEntityDTO dto) {
    MongoTestEntity result = new MongoTestEntity();
    result.setName(dto.getName());
    if(null != dto.getId()) {
      result.setId(dto.getId());
    }
    return result;
  }

  public static final TestEntityDTO entityToDto(MongoTestEntity entity) {
    TestEntityDTO rs = new TestEntityDTO();
    rs.setName(entity.getName());
    rs.setId(entity.getId());
    rs.setCreatedDate(entity.getCreatedDate());
    return rs;
  }

  public static final InternalEventServicePayload entityToInternalPayload(Object entity) {
    InternalEventServicePayload rs = new InternalEventServicePayload();
    rs.setClazzType(entity.getClass().getName());
    rs.setPayload(entity);
    return rs;
  }
}
