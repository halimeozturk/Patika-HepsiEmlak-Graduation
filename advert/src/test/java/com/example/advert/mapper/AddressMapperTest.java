package com.example.advert.mapper;

import com.example.advert.dto.AddressDTO;
import com.example.advert.model.Address;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AddressMapperTest {

    @InjectMocks
    private AddressMapperImpl addressMapper;

    private List<Address> prepareMockAddressList(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(prepareMockAddress());
        return addresses;
    }

    private Address prepareMockAddress(){
        Address address = new Address();
        address.setId(1L);
        address.setProvince("Ankara");
        address.setDistrict("Keçiören");
        return address;
    }

    private List<AddressDTO> prepareMockAddressDTOList(){
        List<AddressDTO> addresses = new ArrayList<>();
        addresses.add(prepareMockAddressDTO());
        return addresses;
    }

    private AddressDTO prepareMockAddressDTO(){
        AddressDTO address = new AddressDTO();
        address.setId(1L);
        address.setProvince("Ankara");
        address.setDistrict("Keçiören");
        return address;
    }

    @Test
    void toEntity(){
        Address address = addressMapper.toEntity(prepareMockAddressDTO());
        assertNotNull(address);
    }

    @Test
    void toDTO(){
        AddressDTO addressDTO = addressMapper.toDTO(prepareMockAddress());
        assertNotNull(addressDTO);
    }

    @Test
    void toEntityList(){
        List<Address> addresses = addressMapper.toEntityList(prepareMockAddressDTOList());
        assertNotNull(addresses);
    }

    @Test
    void toDTOList(){
        List<AddressDTO> addresses= addressMapper.toDTOList(prepareMockAddressList());
        assertNotNull(addresses);
    }
}
