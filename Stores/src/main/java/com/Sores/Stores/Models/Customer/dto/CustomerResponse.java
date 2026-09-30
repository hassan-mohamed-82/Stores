package com.Sores.Stores.Models.Customer.dto;

import com.Sores.Stores.Models.Customer.Entity.Customer;
import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String fax;
    private Instant createdAt;
    private Instant updatedAt;

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getContactInfo() !=null ? customer.getContactInfo().getPhone() :null,
                customer.getContactInfo() !=null ? customer.getContactInfo().getFax() :null,
                customer.getContactInfo() !=null ? customer.getContactInfo().getEmail() :null,
                customer.getUpdatedAt(),
                customer.getCreatedAt()



                );


    }


}
