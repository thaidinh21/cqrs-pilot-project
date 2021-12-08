package dt.pilot.query.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import dt.pilot.query.service.QueryService;
import dt.pilot.shared.entity.MongoTestEntity;
import dt.pilot.shared.exception.BadRequestException;
import dt.pilot.shared.helper.EntityConvertor;
import dt.pilot.shared.pojo.dto.TestEntityDTO;
import dt.pilot.shared.repo.TestEntityRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QueryServiceImpl implements QueryService {

  private final TestEntityRepo repo;

  public QueryServiceImpl(final TestEntityRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<TestEntityDTO> getAll() {
    List<TestEntityDTO> rs =
        this.repo.findAll().stream().map(EntityConvertor::entityToDto).collect(Collectors.toList());
    return rs;
  }

  @Override
  public TestEntityDTO getById(String id) {
    MongoTestEntity e =
        repo.findById(id).orElseThrow(() -> new BadRequestException("The provided id not found"));

    return EntityConvertor.entityToDto(e);

  }

}
