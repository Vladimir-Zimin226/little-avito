package ru.skypro.homework.dto;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;


import javax.annotation.Generated;


@Data
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class CommentsDto {

    private Integer count;

    @Valid
    private List<CommentDto> results = null;

}

