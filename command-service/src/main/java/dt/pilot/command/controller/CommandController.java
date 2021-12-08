package dt.pilot.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dt.pilot.command.service.CommandService;
import dt.pilot.shared.pojo.dto.TestEntityDTO;
import dt.pilot.shared.pojo.response.SimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("/cmd")
@Slf4j
@Tag(name = "Command")
@RestController
public class CommandController {

  private final CommandService service;

  @Autowired
  public CommandController(final CommandService service) {
    this.service = service;
  }


  @Operation(summary = "Create new entity")
  @PostMapping
  public ResponseEntity<SimpleResponse> create(@RequestBody TestEntityDTO body) {
    log.debug("start save entity");

    // should use created() in real life project
    return ResponseEntity.ok(new SimpleResponse(service.create(body)));
  }

  @Operation(summary = "Update entity")
  @PutMapping
  public ResponseEntity<SimpleResponse> update(@RequestBody TestEntityDTO body) {
    log.debug("start save entity");
    // should use created() in real life project
    return ResponseEntity.ok(new SimpleResponse(service.update(body)));
  }

  @Operation(summary = "Update entity")
  @DeleteMapping("/{id}")
  public ResponseEntity<SimpleResponse> delete(@PathVariable("id") String id) {
    log.debug("start save entity");
    // should use created() in real life project
    return ResponseEntity.ok(new SimpleResponse(service.delete(id)));
  }
}
