package ru.skypro.homework.dto;

import java.util.Objects;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class CreateOrUpdateCommentDto {

    private String text;

    public CreateOrUpdateCommentDto text(String text) {
        this.text = text;
        return this;
    }

    /**
     * текст комментария
     *
     * @return text
     */
    @NotNull
    @Size(min = 8, max = 64)
    @Schema(name = "text", description = "текст комментария", required = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateOrUpdateCommentDto createOrUpdateCommentDto = (CreateOrUpdateCommentDto) o;
        return Objects.equals(this.text, createOrUpdateCommentDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateOrUpdateCommentDto {\n");
        sb.append("    text: ").append(toIndentedString(text)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

