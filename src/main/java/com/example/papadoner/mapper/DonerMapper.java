package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DonerMapper {
    public DonerDto toDto(Doner doner);
}