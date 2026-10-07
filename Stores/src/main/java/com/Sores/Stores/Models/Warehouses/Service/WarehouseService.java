package com.Sores.Stores.Models.Warehouses.Service;
import com.Sores.Stores.Models.Warehouses.Entity.Warehouse;
import com.Sores.Stores.Models.Warehouses.Repository.WarehouseRepository;
import com.Sores.Stores.Models.Warehouses.dto.WarehouseRequest;
import com.Sores.Stores.Models.Warehouses.dto.WarehouseResponse;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public List <WarehouseResponse> findAll(){
        return warehouseRepository.findAll()
                .stream()
                .map(WarehouseResponse::from)
                .toList();

    }

    @Transactional
    public WarehouseResponse create(WarehouseRequest warehouse){

      boolean exists = warehouseRepository.existsByName(warehouse.getName());
      if(exists){
          throw new IllegalArgumentException("Warehouse already exists");
      }
          Warehouse request =Warehouse.builder()
                  .name(warehouse.getName())
                  .address(warehouse.getAddress())
                  .build();

      Warehouse savedWarehouse = warehouseRepository.save(request);
      return WarehouseResponse.from(savedWarehouse);
    }

    @Transactional
    public WarehouseResponse update(Long id,WarehouseRequest warehouse){

        Warehouse existwarehouse = warehouseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Warehouse not found"));
        if(existwarehouse.getName().equals(warehouse.getName())){

            if(warehouseRepository.existsByName(warehouse.getName())){
                throw new IllegalArgumentException("Warehouse already exists");
            }

        }
        existwarehouse.setName(warehouse.getName());
        existwarehouse.setAddress(warehouse.getAddress());
        return WarehouseResponse.from(warehouseRepository.save(existwarehouse));
    }

    public WarehouseResponse getbyid(Long id){
        Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Warehouse not found"));
        return WarehouseResponse.from(warehouse);

    }


}
