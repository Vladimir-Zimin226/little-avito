package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;

    public AdServiceImpl(AdRepository adRepository, AdMapper adMapper, ImageService imageService, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
        this.userRepository = userRepository;
    }

    @Override
    public AdDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile file, Authentication authentication) {
        log.info("Creating ad by user: {}", authentication.getName());
        if (createOrUpdateAdDto.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Ad ad = new Ad();
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(BigDecimal.valueOf(createOrUpdateAdDto.getPrice()));
        ad.setDescription(createOrUpdateAdDto.getDescription());
        Image image;
        try {
            image = imageService.downloadImage(file);
        } catch (IOException e) {
            throw new RuntimeException("Photo is not downloaded, something went wrong");
        }
        ad.setAdImage(image);
        ad.setAuthor(user);
        return adMapper.toAdDto(ad);
    }

    @Override
    public ExtendedAdDto getAdById(Long id) {
        log.info("Finding ad by id");
        return adRepository.findById(id).map(adMapper::toExtendedAdDto).orElseThrow(RuntimeException::new);
    }

    @Override
    public AdsDto getAllMyAds(Authentication authentication) {
        log.info("Finding all ads, ows to user: {}", authentication.getName());
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        AdsDto adsDto = new AdsDto();
        adsDto.setResults(user.getAds().stream().map(adMapper::toAdDto).collect(Collectors.toList()));
        return adsDto;
    }

    @Override
    public AdsDto getAllAds() {
        log.info("Finding all ads");
        AdsDto adsDto = new AdsDto();
        adsDto.setResults(adRepository.findAll().stream().map(adMapper::toAdDto).collect(Collectors.toList()));
        return adsDto;
    }

    @Override
    public void removeAd(Long id, Authentication authentication) {
        log.info("Removing ad by its id, ows to user: {}", authentication.getName());
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Ad ad = adRepository.findById(id).orElseThrow(RuntimeException::new);
        adRepository.deleteById(id);
        imageService.deleteImage(ad.getAdImage().getId());
    }

    @Override
    public AdDto updateAd(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication, Long id) {
        log.info("Requesting update ad by id");
        if (createOrUpdateAdDto.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        Ad ad = adRepository.findById(id).orElseThrow(RuntimeException::new);
        ad.setPrice(BigDecimal.valueOf(createOrUpdateAdDto.getPrice()));
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(ad);
        return adMapper.toAdDto(ad);
    }

    @Override
    public byte[] updateImage(MultipartFile file, Authentication authentication, Long id) throws IOException {
        log.info("Updating ad image by its id, ows to user: {}", authentication.getName());
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        Ad updateAdImage = adRepository.findById(id).orElseThrow(RuntimeException::new);
        Long previousImageId = updateAdImage.getAdImage().getId();
        imageService.deleteImage(previousImageId);
        try {
            updateAdImage.setAdImage(imageService.downloadImage(file));
        } catch (IOException e) {
            throw new RuntimeException("Unable to download image");
        }
        adRepository.save(updateAdImage);
        return file.getBytes();
    }
}
