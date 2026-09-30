package com.Sores.Stores.Models.UnitOfMeasure.Controller;


import com.Sores.Stores.Models.UnitOfMeasure.Service.UnitOfMeasureService;
import com.Sores.Stores.Models.UnitOfMeasure.dto.UnitOfMeasureRequest;
import com.Sores.Stores.Models.UnitOfMeasure.dto.UnitOfMeasureResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unit")
@AllArgsConstructor
public class UnitOfMeasureController {

    private final UnitOfMeasureService unitOfMeasureService;

    @PostMapping
    public ResponseEntity<UnitOfMeasureResponse> create(@Valid  @RequestBody UnitOfMeasureRequest Request){
        return ResponseEntity.status(HttpStatus.CREATED).body(unitOfMeasureService.create(Request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitOfMeasureResponse> update(@PathVariable Long id, @Valid  @RequestBody UnitOfMeasureRequest Request){
        return ResponseEntity.ok(unitOfMeasureService.update(id, Request));
    }

    @GetMapping
    public ResponseEntity<List<UnitOfMeasureResponse>> findAll(){
        return ResponseEntity.ok(unitOfMeasureService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UnitOfMeasureResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unitOfMeasureService.getById(id));
    }

}