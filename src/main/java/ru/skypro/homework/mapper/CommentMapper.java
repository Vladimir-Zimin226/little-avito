package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorImage", target = "author.userPhoto.id")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    Comment fromCommentDto(CommentDto dto);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.userPhoto.id", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDto toCommentDto(Comment comment);

    CommentDto createCommentDtoFromComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }

    default LocalDateTime map(Long timestamp) {
        return timestamp != null ? LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC) : null;
    }

    default Long map(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toEpochSecond(ZoneOffset.UTC) : null;
    }
}
