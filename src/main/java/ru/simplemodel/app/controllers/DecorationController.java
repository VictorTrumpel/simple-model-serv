package ru.simplemodel.app.controllers;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import ru.simplemodel.app.dto.decoration.UpdateDecorationDTO;
import ru.simplemodel.app.models.Decoration;
import ru.simplemodel.app.services.DecorationService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/decoration")
public class DecorationController {
  private final DecorationService decorationService;

  @Autowired
  public DecorationController(DecorationService decorationService) {
    this.decorationService = decorationService;
  }

  @PostMapping("/create")
  public ResponseEntity<HttpStatus> create(@RequestBody @Valid Decoration decoration) {
    Optional<Decoration> decorationOptional = decorationService.findByName(decoration.getDecorationName());

    if (decorationOptional.isPresent()) {
      return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    decorationService.save(decoration);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping()
  public List<Decoration> getByIds(@RequestParam List<String> ids) {
    return decorationService.findByIds(ids);
  }

  @GetMapping("/{name}")
  public Decoration getDecorItem(@PathVariable("name") String decorItemName) {
    Optional<Decoration> decorationItem = decorationService.findByName(decorItemName);

    if (decorationItem.isPresent()) {
      return decorationItem.get();
    }

    return null;
  }

  @PutMapping("/{name}")
  public ResponseEntity<HttpStatus> updateDecoration(
    @RequestBody @Valid UpdateDecorationDTO updateDecorationDTO,
    @PathVariable("name") String decorItemName
  ) {

    System.out.println(decorItemName);

    Optional<Decoration> decorationOptional = decorationService.findByName(decorItemName);

    if (decorationOptional.isEmpty()) {
      return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    Decoration decoration = decorationOptional.get();

    decoration.setQuantity(updateDecorationDTO.getQuantity());
    decoration.setStatus(updateDecorationDTO.getStatus());

    decorationService.save(decoration);

    return ResponseEntity.ok(HttpStatus.OK);
  }
}
