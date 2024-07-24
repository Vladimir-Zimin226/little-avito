package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "adImage.image", target = "image")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    AdDto adToAdDto(Ad ad);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "image", target = "adImage.image")
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    Ad adDtoToAd(AdDto dto);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "adImage.image", target = "image")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "id", target = "pk")
    ExtendedAdDto adToExtendedAdDto(Ad ad);

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "authorLastName", target = "author.lastName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "email", target = "author.email")
    @Mapping(source = "image", target = "adImage.image")
    @Mapping(source = "phone", target = "author.phone")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    Ad extendedAdDtoToAd(ExtendedAdDto dto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    Ad createOrUpdateAd(CreateOrUpdateAdDto createOrUpdateAdDto);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
