package com.amsidh.mvc.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class PersonDTO implements Serializable {
    private Long id;
    private String name;
    private Set<AddressDTO> addresses;
}