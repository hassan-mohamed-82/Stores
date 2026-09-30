package com.Sores.Stores.Models.UnitOfMeasure.Service;

import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import com.Sores.Stores.Models.UnitOfMeasure.Repository.UnitOfMeasureRepository;
import com.Sores.Stores.Models.UnitOfMeasure.dto.UnitOfMeasureRequest;
import com.Sores.Stores.Models.UnitOfMeasure.dto.UnitOfMeasureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public List <UnitOfMeasureResponse> findAll(){
        return unitOfMeasureRepository.findAll()
                .stream()
                .map(UnitOfMeasureResponse::from)
                .toList();

    }

    @Transactional
    public UnitOfMeasureResponse create(UnitOfMeasureRequest Request){
        String name = Request.getName().trim();
        boolean existent = unitOfMeasureRepository.existsByName(name);
        if(existent){
            throw new IllegalArgumentException("name already exists");

        }
        UnitOfMeasure Unit = UnitOfMeasure.builder()
                .name(name)
                .build();

        return UnitOfMeasureResponse.from(unitOfMeasureRepository.save(Unit));
    }

    @Transactional
    public UnitOfMeasureResponse update(Long id,UnitOfMeasureRequest Request){

        UnitOfMeasure unit = unitOfMeasureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));

        String name = Request.getName().trim();
        if(unit.getName().equals(name)){
            throw new IllegalArgumentException("name already exists");
        }

        unit.setName(name);
        unit.setUpdatedAt(Instant.now());
        return UnitOfMeasureResponse.from(unitOfMeasureRepository.save(unit));

    }
    public UnitOfMeasureResponse getById(Long id) {
        UnitOfMeasure unit = unitOfMeasureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No unit found with id " + id));
        return UnitOfMeasureResponse.from(unit);
    }
    public UnitOfMeasure getEntityById(Long id) {
        return unitOfMeasureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No unit found with id " + id));
    }


}
