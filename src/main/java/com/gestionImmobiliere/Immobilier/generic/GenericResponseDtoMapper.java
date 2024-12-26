package com.gestionImmobiliere.Immobilier.generic;

public interface GenericResponseDtoMapper<ResponseDTO,Entity> {
    ResponseDTO toResponseDto(Entity entity);
}
