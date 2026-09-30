package com.Sores.Stores.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactInfo {

    @Column(name ="Phone")
    private String phone;
    @Column(name="Email")
    private String email;
    @Column(name = "fax")
    private String fax;
}
