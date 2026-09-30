package com.Sores.Stores.Models.Items.Service;


import com.Sores.Stores.Models.Items.Entity.Item;
import com.Sores.Stores.Models.Items.Repository.ItemRepository;
import com.Sores.Stores.Models.Items.dto.ItemRequest;
import com.Sores.Stores.Models.Items.dto.ItemResponse;
import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import com.Sores.Stores.Models.UnitOfMeasure.Service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UnitOfMeasureService unitOfMeasureService;

    @Transactional
    public ItemResponse create(ItemRequest request) {
        String code = request.getCode().trim();

        if (itemRepository.existsByCode(code)) {
            throw new IllegalArgumentException("An item with code '" + code + "' already exists");
        }

        Set<UnitOfMeasure> units = resolveUnits(request.getUnitIds());

        Item item = Item.builder()
                .code(code)
                .name(request.getName())
                .units(units)
                .build();

        return ItemResponse.from(itemRepository.save(item));
    }

    @Transactional
    public ItemResponse update(Long id, ItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No item found with id " + id));

        String newCode = request.getCode().trim();

        if (!item.getCode().equalsIgnoreCase(newCode) && itemRepository.existsByCode(newCode)) {
            throw new IllegalArgumentException("An item with code '" + newCode + "' already exists");
        }

        item.setCode(newCode);
        item.setName(request.getName());
        item.setUnits(resolveUnits(request.getUnitIds()));
        item.setUpdatedAt(Instant.now());

        return ItemResponse.from(itemRepository.save(item));
    }

    public List<ItemResponse> listAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponse::from)
                .toList();
    }

    public ItemResponse getById(Long id) {
        return ItemResponse.from(getEntityById(id));
    }


    public Item getEntityById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No item found with id " + id));
    }

    private Set<UnitOfMeasure> resolveUnits(Set<Long> unitIds) {
        return unitIds.stream()
                .map(unitOfMeasureService::getEntityById)
                .collect(Collectors.toSet());
    }
}