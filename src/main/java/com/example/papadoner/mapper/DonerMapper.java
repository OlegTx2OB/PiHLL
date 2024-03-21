package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import org.springframework.stereotype.Component;

@Component
public class DonerMapper {
    public DonerDto toDto(Doner doner) {
        return new DonerDto().builder().build();
    }
}
