package com.Sores.Stores.Models.Supplier.Repository;

import com.Sores.Stores.Models.Supplier.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByName(String name);
}