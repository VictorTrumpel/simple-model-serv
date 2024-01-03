package ru.simplemodel.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simplemodel.app.models.Decoration;

public interface DecorationRepository extends JpaRepository<Decoration, Integer> {
  Optional<Decoration> findByDecorationName(String decorationName);
}
