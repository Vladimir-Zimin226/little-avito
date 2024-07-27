package ru.skypro.homework.controller.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.interfaces.AdsApi;
import ru.skypro.homework.dto.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsApiController implements AdsApi {


    @PostMapping(value = "",
            produces = {"application/json"},
            consumes = {"multipart/form-data"})
    public ResponseEntity<AdDto> addAd(@Parameter(name = "properties", description = "", required = true)
                                       @Valid @RequestParam(value = "properties", required = true) CreateOrUpdateAdDto properties,
                                       @Parameter(name = "image", description = "", required = true)
                                       @RequestPart(value = "image", required = true) MultipartFile image) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    public ResponseEntity<ExtendedAdDto> getAds(@Parameter(name = "id", description = "", required = true)
                                                @PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @GetMapping(value = "/me",
            produces = {"application/json"})
    public ResponseEntity<AdsDto> getAdsMe() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @GetMapping(value = "",
            produces = {"application/json"})
    public ResponseEntity<AdsDto> getAllAds() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeAd(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @PatchMapping(value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<AdDto> updateAds(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "CreateOrUpdateAdDto", description = "")
            @Valid @RequestBody(required = false) CreateOrUpdateAdDto createOrUpdateAdDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @PatchMapping(value = "/{id}/image",
            produces = {"application/octet-stream"},
            consumes = {"multipart/form-data"})
    public ResponseEntity<List<byte[]>> updateImage(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
