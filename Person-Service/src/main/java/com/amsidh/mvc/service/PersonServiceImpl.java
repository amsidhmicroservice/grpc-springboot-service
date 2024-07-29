package com.amsidh.mvc.service;

import com.amsidh.mvc.exception.PersonNotFoundException;
import com.amsidh.mvc.model.*;
import com.amsidh.mvc.repository.PersonRepository;
import com.amsidh.mvc.repository.entity.PersonEntity;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
@Slf4j
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    @GrpcClient("address-service")
    private AddressServiceGrpc.AddressServiceBlockingStub addressServiceBlockingStub;

    private final PersonRepository personRepository;

    @Override
    public void getPerson(PersonIdRequest personIdRequest, StreamObserver<PersonResponse> responseObserver) {
        final PersonEntity personEntity = personRepository.findById(personIdRequest.getPersonId()).orElseThrow(() -> new PersonNotFoundException(personIdRequest.getPersonId()));

        final AddressesResponse addressesResponse = addressServiceBlockingStub.getAllAddressByPersonId(AddressPersonIdRequest.newBuilder().setId(personIdRequest.getPersonId()).build());
        responseObserver.onNext(PersonResponse.newBuilder()
                .setId(personEntity.getId())
                .setName(personEntity.getName())
                .setAddresses(addressesResponse)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void createPerson(PersonRequest personRequest, StreamObserver<PersonResponse> responseObserver) {
        PersonEntity personEntity = PersonEntity.builder().id(personRequest.getId()).name(personRequest.getName()).build();
        final PersonEntity savedPersonEntity = personRepository.save(personEntity);
        final AddressesRequest addresses = personRequest.getAddresses();
        final AddressesResponse addressesResponse = addressServiceBlockingStub.saveAddresses(addresses);
        responseObserver.onNext(PersonResponse.newBuilder().setId(savedPersonEntity.getId()).setName(savedPersonEntity.getName())
                .setAddresses(addressesResponse)
                .build());
        responseObserver.onCompleted();
    }
}