package com.Sores.Stores.Models.Supplier.Service;


import com.Sores.Stores.Models.Supplier.Entity.Supplier;
import com.Sores.Stores.Models.Supplier.Repository.SupplierRepository;
import com.Sores.Stores.Models.Supplier.dto.SupplierRequest;
import com.Sores.Stores.Models.Supplier.dto.SupplierResponse;
import com.Sores.Stores.common.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Transactional
    public SupplierResponse create(SupplierRequest request) {
        String name = request.getName().trim();

        if (supplierRepository.existsByName(name)) {
            throw new IllegalArgumentException("A supplier named '" + name + "' already exists");
        }

        Supplier supplier = Supplier.builder()
                .name(name)
                .contactInfo(toContactInfo(request))
                .build();

        return SupplierResponse.from(supplierRepository.save(supplier));
    }

    @Transactional
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No supplier found with id " + id));

        String newName = request.getName().trim();

        if (!supplier.getName().equalsIgnoreCase(newName) && supplierRepository.existsByName(newName)) {
            throw new IllegalArgumentException("A supplier named '" + newName + "' already exists");
        }

        supplier.setName(newName);
        supplier.setContactInfo(toContactInfo(request));
        supplier.setUpdatedAt(Instant.now());

        return SupplierResponse.from(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> listAll() {
        return supplierRepository.findAll().stream()
                .map(SupplierResponse::from)
                .toList();
    }

    public SupplierResponse getById(Long id) {
        return SupplierResponse.from(getEntityById(id));
    }

    public Supplier getEntityById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No supplier found with id " + id));
    }


    private ContactInfo toContactInfo(SupplierRequest request) {
        return ContactInfo.builder()
                .phone(request.getPhone())
                .fax(request.getFax())
                .email(request.getEmail())
                .build();
    }
}