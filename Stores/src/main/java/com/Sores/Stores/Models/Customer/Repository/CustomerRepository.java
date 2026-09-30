package com.Sores.Stores.Models.Customer.Repository;

import com.Sores.Stores.Models.Customer.Entity.Customer;
import com.Sores.Stores.Models.Customer.dto.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {



    boolean existsByName(String name);

}
