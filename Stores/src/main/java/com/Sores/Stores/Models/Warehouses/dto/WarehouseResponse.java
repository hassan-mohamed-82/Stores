package com.Sores.Stores.Models.Warehouses.dto;

import com.Sores.Stores.Models.Warehouses.Entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class WarehouseResponse {
    private Long id;
    private String name;
    private String address;
    private Instant createdAt;
    private Instant updatedAt;

    public static WarehouseResponse from(Warehouse warehouse) {
        return new WarehouseResponse(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress(),
                warehouse.getCreatedAt(),
                warehouse.getUpdatedAt()
        );


    }


}
