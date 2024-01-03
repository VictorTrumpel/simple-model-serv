package ru.simplemodel.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.simplemodel.app.models.Decoration;
import java.util.List;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, String> {
  Optional<Decoration> findByDecorationName(String decorationName);

  List<Decoration> findAllByDecorationNameIn(List<String> ids);
}
