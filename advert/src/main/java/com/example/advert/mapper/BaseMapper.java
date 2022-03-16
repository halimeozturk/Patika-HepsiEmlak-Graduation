package com.example.advert.mapper;



import com.example.advert.dto.BaseEntityDTO;
import com.example.advert.model.BaseEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

public interface BaseMapper<Entity extends BaseEntity, DTO extends BaseEntityDTO> {

    @Named("toEntity")
    @Mapping(target = "creationDate", ignore = true)
    Entity toEntity(DTO dto);

    @Named("toDTO")
    DTO toDTO(Entity entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Entity> toEntityList(List<DTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<DTO> toDTOList(List<Entity> entityList);


}
