package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "authorImage", target = "author.userPhoto.image")
    @Mapping(source = "authorFirstName", target = "author.firstName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "text", target = "text")
    Comment commentDtoToComment(CommentDto dto);


    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.userPhoto.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "text", target = "text")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "text", target = "text")
    CommentDto createOrUpdateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
