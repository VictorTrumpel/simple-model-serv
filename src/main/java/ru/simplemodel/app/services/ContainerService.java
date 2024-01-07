package ru.simplemodel.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.simplemodel.app.models.Container;
import ru.simplemodel.app.repositories.ContainerRepository;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContainerService {
  private final ContainerRepository containerRepository;

  @Autowired
  public ContainerService(ContainerRepository containerRepository) {
    this.containerRepository = containerRepository;
  }

  @Transactional
  public void add(Container container) {
    containerRepository.save(container);
  }
  
  @Transactional
  public void deleteById(String containerName) {
    containerRepository.deleteById(containerName);
  }

  public List<Container> getAll() {
    return containerRepository.findAll();
  }
}
