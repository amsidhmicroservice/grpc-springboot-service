package com.amsidh.mvc.dto;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AddressDTO implements Serializable {
    private Long id;
    private String address;
    private Long personId;
}