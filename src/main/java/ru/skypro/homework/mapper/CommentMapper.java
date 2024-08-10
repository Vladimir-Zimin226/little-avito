package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(target = "author.userPhoto.id", expression = "java(pathToImageFromDto(commentDto))")
    Comment fromCommentDto(CommentDto dto);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(imageMapperForDto(comment))")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDto toCommentDto(Comment comment);

    CommentDto createCommentDtoFromComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    default Integer pathToImageFromDto(CommentDto dto) {
        String imagePath = dto.getAuthorImage();
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

    default String imageMapperForDto(Comment comment) {
        int id = comment.getId();
        return "/comments/" + id + "/image";
    }


    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }

    default LocalDateTime map(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    default Long map(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zoneId).toInstant();
        return instant.toEpochMilli();
    }
}
