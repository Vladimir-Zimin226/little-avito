package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final CommentService commentService;

    /**
     * Создает новое объявление.
     *
     * @param createOrUpdateAdDto данные объявления.
     * @param file изображение объявления.
     * @param authentication информация о текущем пользователе.
     * @return DTO созданного объявления.
     * @throws IllegalArgumentException если цена отрицательная.
     * @throws RuntimeException если не удалось загрузить изображение.
     * @throws UserNotFoundException если пользователь не найден.
     */
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
        Ad savedAd = adRepository.save(ad);
        return adMapper.toAdDto(savedAd);
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return DTO объявления.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Override
    public ExtendedAdDto getAdById(Integer id) {
        log.info("Finding ad by id");
        return adRepository.findById(id).map(adMapper::toExtendedAdDto).orElseThrow(AdNotFoundException::new);
    }

    /**
     * Получает все объявления текущего пользователя.
     *
     * @param authentication информация о текущем пользователе.
     * @return DTO всех объявлений пользователя.
     * @throws UserNotFoundException если пользователь не найден.
     */
    @Override
    public AdsDto getAllMyAds(Authentication authentication) {
        log.info("Finding all ads, ows to user: {}", authentication.getName());
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        AdsDto adsDto = new AdsDto();
        adsDto.setResults(user.getAds().stream().map(adMapper::toAdDto).collect(Collectors.toList()));
        return adsDto;
    }

    /**
     * Получает все объявления.
     *
     * @return DTO всех объявлений.
     */
    @Override
    public AdsDto getAllAds() {
        log.info("Finding all ads");
        AdsDto adsDto = new AdsDto();
        adsDto.setResults(adRepository.findAll().stream().map(adMapper::toAdDto).collect(Collectors.toList()));
        return adsDto;
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @param authentication информация о текущем пользователе.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Transactional
    @Override
    public void removeAd(Integer id, Authentication authentication) {
        log.info("Removing ad by its id, ows to user: {}", authentication.getName());
        Ad ad = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        commentService.deleteAllByAdId(id);
        adRepository.deleteById(id);
        imageService.deleteImage(ad.getAdImage().getId());
    }

    /**
     * Обновляет объявление.
     *
     * @param createOrUpdateAdDto новые данные объявления.
     * @param authentication информация о текущем пользователе.
     * @param id идентификатор объявления для обновления.
     * @return DTO обновленного объявления.
     * @throws IllegalArgumentException если цена отрицательная.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Override
    public AdDto updateAd(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication, Integer id) {
        log.info("Requesting update ad by id");
        if (createOrUpdateAdDto.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        Ad ad = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        ad.setPrice(BigDecimal.valueOf(createOrUpdateAdDto.getPrice()));
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(ad);
        return adMapper.toAdDto(ad);
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param file новое изображение объявления.
     * @param authentication информация о текущем пользователе.
     * @param id идентификатор объявления для обновления.
     * @return пустой массив байтов.
     * @throws IOException если не удалось загрузить изображение.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Transactional
    @Override
    public byte[] updateImage(MultipartFile file, Authentication authentication, Integer id){
        log.info("Updating ad image by its id, ows to user: {}", authentication.getName());
        Ad updateAdImage = adRepository.findById(id).orElseThrow(AdNotFoundException::new);
        Integer previousImageId = updateAdImage.getAdImage().getId();
        imageService.deleteImage(previousImageId);
        try {
            updateAdImage.setAdImage(imageService.downloadImage(file));
        } catch (IOException e) {
            throw new RuntimeException("Unable to download image");
        }
        adRepository.save(updateAdImage);
        return new byte[0];
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return объявление.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Override
    public Ad getAd(Integer id) {
        return adRepository.findById(id).orElseThrow(AdNotFoundException::new);
    }

    /**
     * Получает изображение объявления.
     *
     * @param adId идентификатор объявления.
     * @return изображение в виде массива байтов.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Transactional
    @Override
    public byte[] getAdImage(Integer adId) {
        log.info("getting image of an AD with a ID: {}", adId);
        return imageService.getImage(adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getAdImage().getId());
    }
}
