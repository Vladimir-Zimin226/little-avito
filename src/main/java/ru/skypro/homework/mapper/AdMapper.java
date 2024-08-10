package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "image",expression = "java(imageMapperToDto(ad))")
    @Mapping(source = "id", target = "pk")
    AdDto toAdDto(Ad ad);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "id")
    @Mapping(target = "adImage.id",expression = "java(pathToImage(adDto))")
    Ad fromAdDto(AdDto dto);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(target = "image", expression = "java(imageMapperToDto(ad))")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "id", target = "pk")
    ExtendedAdDto toExtendedAdDto(Ad ad);

    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "authorLastName", target = "author.lastName")
    @Mapping(source = "email", target = "author.email")
    @Mapping(source = "phone", target = "author.phone")
    @Mapping(target = "adImage.id", expression = "java(pathToImageForExtendedAdDto(extendedAdDto))")
    @Mapping(source = "pk", target = "id", ignore = true)
    Ad fromExtendedAdDto(ExtendedAdDto dto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    Ad createAdFromDto(CreateOrUpdateAdDto createOrUpdateAdDto);

    default Integer pathToImage(AdDto adDto) {
        String imagePath = adDto.getImage();
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        Image image = new Image();
        try {
            Integer id = extractIdFromPath(imagePath);
            image.setId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid image path: " + imagePath, e);
        }
        return image.getId();
    }

    default Integer pathToImageForExtendedAdDto(ExtendedAdDto adDto) {
        String imagePath = adDto.getImage();
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        Image image = new Image();
        try {
            Integer id = extractIdFromPath(imagePath);
            image.setId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid image path: " + imagePath, e);
        }
        return image.getId();
    }

    private Integer extractIdFromPath(String path) {
        try {
            String[] parts = path.split("/");
            return Integer.valueOf(parts[2]); // Получаем id из пути
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid path format: " + path, e);
        }
    }

    default String imageMapperToDto(Ad ad) {
        Integer id = ad.getId();
        return "/ads/" + id + "/image";
    }

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
