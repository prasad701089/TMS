package com.TollManagementSystem.TMS.mapper;

import com.TollManagementSystem.TMS.dto.TollTransactionDto;
import com.TollManagementSystem.TMS.entity.TollTransaction;

public class TollTransactionMapper {
    public static TollTransactionDto toDto(TollTransaction entity) {
        TollTransactionDto dto = new TollTransactionDto();
        dto.setId(entity.getId());
        dto.setVehicle(entity.getVehicle());
        dto.setTollAmount(entity.getTollAmount());
        dto.setTimestamp(entity.getTimestamp());
        return dto;
    }

    public static TollTransaction toEntity(TollTransactionDto dto) {
        TollTransaction entity = new TollTransaction();
        entity.setId(dto.getId());
        entity.setVehicle(dto.getVehicle());
        entity.setTollAmount(dto.getTollAmount());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }
}
