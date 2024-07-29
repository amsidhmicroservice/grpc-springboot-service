/*
package com.amsidh.mvc.controller;

import com.amsidh.mvc.dto.AddressDTO;
import com.amsidh.mvc.dto.PersonDTO;
import com.amsidh.mvc.dto.PersonResponseDTO;
import com.amsidh.mvc.model.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @GrpcClient("person-service")
    private PersonServiceGrpc.PersonServiceBlockingStub personClient;

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        final PersonResponse personResponse = this.personClient.getPerson(PersonIdRequest.newBuilder().setPersonId(id).build());
        final Set<AddressDTO> addressDTOSet = personResponse.getAddresses().getAddressesList().stream().map(addressResponse -> AddressDTO.builder()
                        .id(addressResponse.getId()).address(addressResponse.getAddress()).build())
                .collect(Collectors.toSet());
        return PersonDTO.builder().id(personResponse.getId()).name(personResponse.getName())
                .addresses(addressDTOSet)
                .build();
    }

    @PostMapping
    public PersonResponseDTO createPerson(@RequestBody PersonDTO personDTO) {
        final AddressesRequest.Builder builder = AddressesRequest.newBuilder();
        personDTO.getAddresses().forEach(addressDTO -> {
            final AddressRequest addressRequest = AddressRequest
                    .newBuilder()
                    .setId(addressDTO.getId())
                    .setAddress(addressDTO.getAddress())
                    .setPersonId(personDTO.getId())
                    .build();
            builder.addAddresses(addressRequest);
        });

        final PersonRequest personRequest = PersonRequest.newBuilder()
                .setId(personDTO.getId())
                .setName(personDTO.getName())
                .setAddresses(builder.build())
                .build();
        final PersonResponse personResponse = this.personClient.createPerson(personRequest);

        final Set<AddressDTO> addressDTOSet = personResponse.getAddresses().getAddressesList().stream().map(addressResponse -> AddressDTO
                .builder()
                .id(addressResponse.getId())
                .address(addressResponse.getAddress())
                .build()).collect(Collectors.toSet());
        return PersonResponseDTO.builder()
                .id(personResponse.getId())
                .name(personDTO.getName())
                .addresses(addressDTOSet)
                .build();
    }
}*/
