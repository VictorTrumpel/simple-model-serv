package ru.simplemodel.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplemodel.app.models.Decoration;
import ru.simplemodel.app.repositories.DecorationRepository;

@Service
@Transactional(readOnly = true)
public class DecorationService {

  private final DecorationRepository decorationRepository;

  @Autowired
  public DecorationService(DecorationRepository decorationRepository) {
    this.decorationRepository = decorationRepository;
  }

  @Transactional
  public void save(Decoration decoration) {
    this.decorationRepository.save(decoration);
  }

  public Optional<Decoration> findByName(String decorItemName) {
    return this.decorationRepository.findByDecorationName(decorItemName);
  }

  public List<Decoration> findByIds(List<String> ids) {
    return this.decorationRepository.findAllByDecorationNameIn(ids);
  }
}
