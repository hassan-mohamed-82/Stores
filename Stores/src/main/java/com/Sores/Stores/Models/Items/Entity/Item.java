package com.Sores.Stores.Models.Items.Entity;

import com.Sores.Stores.Models.UnitOfMeasure.Entity.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items",uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code",nullable = false,unique = true)
    private String code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_units",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    @Builder.Default
    private Set<UnitOfMeasure> units = new HashSet<>();



    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Builder.Default
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();
}