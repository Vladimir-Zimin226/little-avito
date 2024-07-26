package ru.skypro.homework.dto;

import java.util.List;
import javax.validation.Valid;
import lombok.Data;

@Data
public class AdsDto {

    private Integer count;

    @Valid
    private List<AdDto> results;

}

