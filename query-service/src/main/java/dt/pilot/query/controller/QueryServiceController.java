package dt.pilot.query.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import dt.pilot.query.service.QueryService;
import dt.pilot.shared.pojo.dto.TestEntityDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "qry")
public class QueryServiceController {
  private final QueryService queryService;


  @Autowired
  public QueryServiceController(final QueryService queryService) {
    this.queryService = queryService;
  }

  @GetMapping
  public ResponseEntity<List<TestEntityDTO>> getAllData() {
    return ResponseEntity.ok(queryService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TestEntityDTO> getOne(@PathVariable("id") String id) {
    return ResponseEntity.ok(queryService.getById(id));
  }

}
