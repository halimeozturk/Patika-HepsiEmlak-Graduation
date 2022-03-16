package com.example.advert.mapper;



import com.example.advert.dto.ImageDTO;
import com.example.advert.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends BaseMapper<Image, ImageDTO>{
}
