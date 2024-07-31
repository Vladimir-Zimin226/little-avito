package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;

public interface AdService {

    AdDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile file, Authentication authentication) throws IOException;

    ExtendedAdDto getAdById(Long id);

    AdsDto getAllMyAds(Authentication authentication);

    AdsDto getAllAds();

    void removeAd(Long id, Authentication authentication);

    AdDto updateAd(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication, Long id);

    byte[] updateImage(MultipartFile file, Authentication authentication, Long id) throws IOException;

}
