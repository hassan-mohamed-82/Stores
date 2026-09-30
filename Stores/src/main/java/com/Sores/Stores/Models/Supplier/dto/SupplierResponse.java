package com.Sores.Stores.Models.Supplier.dto;

import com.Sores.Stores.Models.Supplier.Entity.Supplier;
import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SupplierResponse {
    private Long id;
    private String name;
    private String phone;
    private String fax;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;

    public static SupplierResponse from(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactInfo() != null ? supplier.getContactInfo().getPhone() : null,
                supplier.getContactInfo() != null ? supplier.getContactInfo().getFax() : null,
                supplier.getContactInfo() != null ? supplier.getContactInfo().getEmail() : null,
                supplier.getCreatedAt(),
                supplier.getUpdatedAt()
        );
    }
}