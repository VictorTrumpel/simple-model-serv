package ru.simplemodel.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.simplemodel.app.models.Container;

@Repository
public interface ContainerRepository extends JpaRepository<Container, String> {}
