package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;


    @Override
    public Image downloadImage(MultipartFile file) throws IOException {
        log.info("Downloading the image");
        Image image = new Image();
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        image.setFileSize(file.getSize());
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Long id) {
        log.info("Deleting the image by its id");
        imageRepository.deleteById(id);
    }

    @Override
    public byte[] getImage(Long id){
        return imageRepository.findById(id).orElseThrow(RuntimeException::new).getData();
    }
}
