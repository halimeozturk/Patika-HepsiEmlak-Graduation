package com.example.advert.mapper;

import com.example.advert.dto.AddressDTO;
import com.example.advert.dto.ImageDTO;
import com.example.advert.model.Address;
import com.example.advert.model.Image;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ImageMapperTest {

    @InjectMocks
    private ImageMapperImpl imageMapper;

    private List<Image> prepareMockAddressList(){
        List<Image> images = new ArrayList<>();
        images.add(prepareMockImage());
        return images;
    }

    private Image prepareMockImage(){
        Image image = new Image();
        image.setId(1L);
        image.setPath("https://...");
        return image;
    }

    private List<ImageDTO> prepareMockImageDTOList(){
        List<ImageDTO> imageDTOS = new ArrayList<>();
        imageDTOS.add(prepareMockImageDTO());
        return imageDTOS;
    }

    private ImageDTO prepareMockImageDTO(){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(1L);
        imageDTO.setPath("https://...");
        return imageDTO;
    }

    @Test
    void toEntity(){
        Image image = imageMapper.toEntity(prepareMockImageDTO());
        assertNotNull(image);
    }

    @Test
    void toDTO(){
        ImageDTO imageDTO = imageMapper.toDTO(prepareMockImage());
        assertNotNull(imageDTO);
    }

    @Test
    void toEntityList(){
        List<Image> images = imageMapper.toEntityList(prepareMockImageDTOList());
        assertNotNull(images);
    }

    @Test
    void toDTOList(){
        List<ImageDTO> imageDTOS= imageMapper.toDTOList(prepareMockAddressList());
        assertNotNull(imageDTOS);
    }
}
