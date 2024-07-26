package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "adImage.id", target = "image")
    @Mapping(source = "id", target = "pk")
    AdDto toAdDto(Ad ad);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "image", target = "adImage.id")
    @Mapping(source = "pk", target = "id")
    Ad fromAdDto(AdDto dto);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "adImage.id", target = "image")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "id", target = "pk")
    ExtendedAdDto toExtendedAdDto(Ad ad);

    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "authorLastName", target = "author.lastName")
    @Mapping(source = "email", target = "author.email")
    @Mapping(source = "image", target = "adImage.id")
    @Mapping(source = "phone", target = "author.phone")
    @Mapping(source = "pk", target = "id", ignore = true)
    Ad fromExtendedAdDto(ExtendedAdDto dto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    Ad createAdFromDto(CreateOrUpdateAdDto createOrUpdateAdDto);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
