package com.Sores.Stores.Models.UnitOfMeasure.Repository;

import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure,Long> {

    boolean existsByName(String name);

    Optional<UnitOfMeasure> findByName(String name);
}
