package dt.pilot.query.service;

import java.util.List;
import dt.pilot.shared.pojo.dto.TestEntityDTO;

public interface QueryService {

  public List<TestEntityDTO> getAll();

  public TestEntityDTO getById(String id);
}
