package com.Sores.Stores.Models.UnitOfMeasure.dto;

import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class UnitOfMeasureResponse {
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public static UnitOfMeasureResponse from(UnitOfMeasure unitOfMeasure) {
        return new UnitOfMeasureResponse(
                unitOfMeasure.getId(),
                unitOfMeasure.getName(),
                unitOfMeasure.getCreatedAt(),
                unitOfMeasure.getUpdatedAt()
        );


    }


}
