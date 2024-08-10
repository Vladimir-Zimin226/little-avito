package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

import java.io.IOException;

public interface AdService {

    AdDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile file, Authentication authentication) throws IOException;

    ExtendedAdDto getAdById(Integer id);

    AdsDto getAllMyAds(Authentication authentication);

    AdsDto getAllAds();

    void removeAd(Integer id, Authentication authentication);

    AdDto updateAd(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication, Integer id);

    byte[] updateImage(MultipartFile file, Authentication authentication, Integer id) throws IOException;

    Ad getAd(Integer id);

    byte[] getAdImage(Integer adId);

}
