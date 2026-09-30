package com.Sores.Stores.Models.Supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {

    @NotBlank(message = "name is required")
    private String name;

    private String phone;
    private String fax;

    @Email(message = "email must be a valid email address")
    private String email;

}