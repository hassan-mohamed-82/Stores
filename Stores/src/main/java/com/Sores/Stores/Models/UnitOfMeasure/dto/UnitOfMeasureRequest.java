package com.Sores.Stores.Models.UnitOfMeasure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitOfMeasureRequest {

    @NotBlank(message = "name is required")
    private String name;



}