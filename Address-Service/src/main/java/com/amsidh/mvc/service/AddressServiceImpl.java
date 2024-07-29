package com.amsidh.mvc.service;

import com.amsidh.mvc.exception.AddressNotFoundException;
import com.amsidh.mvc.model.*;
import com.amsidh.mvc.repository.AddressRepository;
import com.amsidh.mvc.repository.entity.AddressEntity;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@GrpcService
public class AddressServiceImpl extends AddressServiceGrpc.AddressServiceImplBase {

    private final AddressRepository addressRepository;

    @Override
    public void getAddress(AddressIdRequest request, StreamObserver<AddressResponse> responseObserver) {
        final AddressEntity addressEntity = addressRepository.findById(request.getId()).orElseThrow(() -> new AddressNotFoundException(request.getId()));
        responseObserver.onNext(AddressResponse.newBuilder().setId(addressEntity.getId())
                .setAddress(addressEntity.getAddress())
                .setPersonId(addressEntity.getPersonId())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void createAddress(AddressRequest addressRequest, StreamObserver<AddressResponse> responseObserver) {
        final AddressEntity addressEntity = AddressEntity.builder()
                .id(addressRequest.getId())
                .address(addressRequest.getAddress())
                .personId(addressRequest.getPersonId())
                .build();
        final AddressEntity savedAddressEntity = addressRepository.save(addressEntity);
        responseObserver.onNext(AddressResponse.newBuilder()
                .setId(savedAddressEntity.getId())
                .setAddress(savedAddressEntity.getAddress())
                .setPersonId(savedAddressEntity.getPersonId())
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void saveAddresses(AddressesRequest addressesRequest, StreamObserver<AddressesResponse> responseObserver) {
        final Set<AddressEntity> addressEntitySet = addressesRequest.getAddressesList().stream().map(addressRequest -> AddressEntity.builder()
                .id(addressRequest.getId())
                .address(addressRequest.getAddress())
                .personId(addressRequest.getPersonId())
                .build()).collect(Collectors.toSet());
        final List<AddressEntity> addressEntities = addressRepository.saveAll(addressEntitySet);
        final Set<AddressResponse> collect = addressEntities.stream()
                .map(addressEntity -> AddressResponse.newBuilder().setId(addressEntity.getId())
                        .setAddress(addressEntity.getAddress())
                        .setPersonId(addressEntity.getPersonId())
                        .build()).collect(Collectors.toSet());

        responseObserver.onNext(AddressesResponse.newBuilder()
                .addAllAddresses(collect)
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void getAllAddressByPersonId(AddressPersonIdRequest addressPersonIdRequest, StreamObserver<AddressesResponse> responseObserver) {
        final List<AddressEntity> byPersonId = addressRepository.findByPersonId(addressPersonIdRequest.getId());
        AddressesResponse addressesResponse = AddressesResponse.newBuilder().addAllAddresses(
                        byPersonId.stream().map(addressEntity -> AddressResponse.newBuilder().setId(addressEntity.getId())
                                .setAddress(addressEntity.getAddress())
                                .setPersonId(addressEntity.getPersonId())
                                .build()).collect(Collectors.toSet()))
                .build();
        responseObserver.onNext(addressesResponse);

        responseObserver.onCompleted();
    }
}
