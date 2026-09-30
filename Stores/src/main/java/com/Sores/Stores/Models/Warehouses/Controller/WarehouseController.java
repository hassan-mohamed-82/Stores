package com.Sores.Stores.Models.Warehouses.Controller;

import com.Sores.Stores.Models.Warehouses.Service.WarehouseService;
import com.Sores.Stores.Models.Warehouses.dto.WarehouseRequest;
import com.Sores.Stores.Models.Warehouses.dto.WarehouseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> listAllWarehouses() {
        return ResponseEntity.ok(
                warehouseService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getbyid(id));
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> create(
            @Valid @RequestBody WarehouseRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(warehouseService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequest request
    ) {
        return ResponseEntity.ok(warehouseService.update(id, request));
    }
}