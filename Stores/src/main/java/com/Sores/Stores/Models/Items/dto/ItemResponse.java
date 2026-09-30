package com.Sores.Stores.Models.Items.dto;

import com.Sores.Stores.Models.Items.Entity.Item;
import com.Sores.Stores.Models.UnitOfMeasure.dto.UnitOfMeasureResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String code;
    private String name;
    private Set<UnitOfMeasureResponse> units;
    private Instant createdAt;
    private Instant updatedAt;

    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getCode(),
                item.getName(),
                item.getUnits().stream()
                        .map(UnitOfMeasureResponse::from)
                        .collect(Collectors.toSet()),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }


    }



