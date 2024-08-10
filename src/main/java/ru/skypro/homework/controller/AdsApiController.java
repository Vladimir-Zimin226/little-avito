package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsApiController{

    private final AdService adService;

    public AdsApiController(AdService adService) {
        this.adService = adService;
    }


    @PostMapping(value = "",
            produces = {"application/json"},
            consumes = {"multipart/form-data"})
    public ResponseEntity<AdDto> addAds(@Parameter(name = "properties", required = true)
                                       @Valid @RequestPart(value = "properties") CreateOrUpdateAdDto properties,
                                       @Parameter(name = "image", required = true)
                                       @RequestPart(value = "image") MultipartFile image,
                                       Authentication authentication) throws IOException {
        return ResponseEntity.ok(adService.createAd(properties, image, authentication));

    }


    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    public ResponseEntity<ExtendedAdDto> getAds(@Parameter(name = "id", required = true)
                                                @PathVariable("id") Integer id) {
        return ResponseEntity.ok(adService.getAdById(id));
    }


    @GetMapping(value = "/me",
            produces = {"application/json"})
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAllMyAds(authentication));
    }


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }


    @PreAuthorize("@adServiceImpl.getAd(#id).email.equals(authentication.name) or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeAd(
            @Parameter(name = "id", required = true) @PathVariable("id") Integer id, Authentication authentication) {
        adService.removeAd(id, authentication);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("@adServiceImpl.getAd(#id).email.equals(authentication.name) or hasAuthority('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<AdDto> updateAds(
            @Parameter(name = "id",required = true) @PathVariable("id") Integer id,
            @Parameter(name = "CreateOrUpdateAdDto")
            @Valid @RequestBody(required = false) CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication) {
        return ResponseEntity.ok(adService.updateAd(createOrUpdateAdDto, authentication, id));
    }

    @PreAuthorize("@adServiceImpl.getAd(#id).email.equals(authentication.name) or hasAuthority('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/image",
            produces = {"application/octet-stream"},
            consumes = {"multipart/form-data"})
    public ResponseEntity<List<byte[]>> updateImage(
            @Parameter(name = "id", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "image", required = true) @RequestPart(value = "image") MultipartFile image, Authentication authentication) throws IOException {
        return ResponseEntity.ok(Collections.singletonList(adService.updateImage(image, authentication,id)));
    }

    @GetMapping(
            value = "/{id}/image",
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id){
        return ResponseEntity.ok(adService.getAdImage(id));
    }

}
