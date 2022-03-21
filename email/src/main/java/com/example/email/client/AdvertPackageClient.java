package com.example.email.client;

import com.example.email.dto.AdvertPackageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "advertPackageClient", url = "http://localhost:8088/advert-packages")
public interface AdvertPackageClient {

    @GetMapping("/{id}")
    AdvertPackageDTO getById(@PathVariable Long id);
}
