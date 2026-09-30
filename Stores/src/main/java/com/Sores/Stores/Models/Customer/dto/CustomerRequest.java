package com.Sores.Stores.Models.Customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @NotBlank(message = "name is required")
    private String name;
    @Email(message = "email must be a valid email address")
    private String email;
    @NotBlank(message = "Phone is required")
    private String phone;
    private String fax;




}