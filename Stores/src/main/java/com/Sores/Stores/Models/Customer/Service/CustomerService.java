package com.Sores.Stores.Models.Customer.Service;


import com.Sores.Stores.Models.Customer.Entity.Customer;
import com.Sores.Stores.Models.Customer.Repository.CustomerRepository;
import com.Sores.Stores.Models.Customer.dto.CustomerRequest;
import com.Sores.Stores.Models.Customer.dto.CustomerResponse;
import com.Sores.Stores.common.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List <CustomerResponse> findAll(){
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponse::from)
                .toList();

    }

    @Transactional
    public CustomerResponse create(CustomerRequest Request){
        String name = Request.getName().trim();
        boolean existent = customerRepository.existsByName(name);
        if(existent){
            throw new IllegalArgumentException("name already exists");

        }
        Customer customer = Customer.builder()
                .name(name)
                .contactInfo(toContactInfo(Request))
                .build();

        return CustomerResponse.from(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponse update(Long id,CustomerRequest Request){

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));

        String newName = Request.getName().trim();

        if (!customer.getName().equalsIgnoreCase(newName) && customerRepository.existsByName(newName)) {
            throw new IllegalArgumentException("A customer named '" + newName + "' already exists");
        }

        customer.setName(newName);
        customer.setContactInfo(toContactInfo(Request));
        customer.setUpdatedAt(Instant.now());

        return CustomerResponse.from(customerRepository.save(customer));

    }



    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No unit found with id " + id));
        return CustomerResponse.from(customer);
    }

    private ContactInfo toContactInfo(CustomerRequest request) {
        return ContactInfo.builder()
                .phone(request.getPhone())
                .fax(request.getFax())
                .email(request.getEmail())
                .build();
    }

}
