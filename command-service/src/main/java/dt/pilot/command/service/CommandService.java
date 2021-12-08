package dt.pilot.command.service;

import dt.pilot.shared.pojo.dto.TestEntityDTO;

public interface CommandService {
  String create(TestEntityDTO body);

  String update(TestEntityDTO body);

  String delete(String identity);
}
