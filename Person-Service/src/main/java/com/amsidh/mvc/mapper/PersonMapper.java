package com.amsidh.mvc.mapper;

import com.amsidh.mvc.dto.PersonDTO;
import com.amsidh.mvc.repository.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDto(PersonEntity person);
    PersonEntity toEntity(PersonDTO personDTO);
}