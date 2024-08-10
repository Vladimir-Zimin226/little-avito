package ru.skypro.homework.dto;

import java.util.List;
import javax.validation.Valid;
import lombok.Data;


@Data
public class CommentsDto {

    private Integer count;

    @Valid
    private List<CommentDto> results;

}

