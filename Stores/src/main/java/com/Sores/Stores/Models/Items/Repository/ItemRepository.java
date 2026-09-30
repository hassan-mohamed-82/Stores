package com.Sores.Stores.Models.Items.Repository;

import com.Sores.Stores.Models.Items.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
