package ru.simplemodel.app.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import ru.simplemodel.app.models.Container;
import ru.simplemodel.app.services.ContainerService;
import ru.simplemodel.app.yAPI.YFileApi;
import java.util.*;

@RestController
@RequestMapping("/container")
public class ContainerController {

  private YFileApi yFileApi;

  private ContainerService containerService;

  @Autowired
  public ContainerController(YFileApi yFileApi, ContainerService containerService) {
    this.yFileApi = yFileApi;
    this.containerService = containerService;
  }
  
  @PostMapping("/upload")
  public ResponseEntity<String> uploadContainer(
    @RequestParam("files") List<MultipartFile> files, 
    @RequestParam("containerName") String containerName
  ) throws Exception {
    yFileApi.createDirectory(containerName);

    for (MultipartFile file : files) {
      String filePath = containerName + "/" + file.getOriginalFilename();

      yFileApi.uploadFile(file, filePath);
    }

    Container container = new Container();
    container.setContainerName(containerName);

    containerService.add(container);

    return ResponseEntity.ok("Files uploaded successfully");
  }


  @DeleteMapping("/delete/{name}")
  public ResponseEntity<String> deleteContainer(
    @PathVariable("name") String containerName
  ) throws Exception {
    yFileApi.deleteDirectory(containerName);

    containerService.deleteById(containerName);

    return ResponseEntity.ok("Files deleted successfully");
  }

  @GetMapping("/{name}")
  public JsonNode getContainerInfo(@PathVariable("name") String containerName) throws Exception {
    return yFileApi.getDirInfo(containerName);
  }

  @ExceptionHandler
  private ResponseEntity<String> handleException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
