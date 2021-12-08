package dt.pilot.shared.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import dt.pilot.shared.entity.MongoTestEntity;

@Repository
public interface TestEntityRepo extends MongoRepository<MongoTestEntity, String> {

}
