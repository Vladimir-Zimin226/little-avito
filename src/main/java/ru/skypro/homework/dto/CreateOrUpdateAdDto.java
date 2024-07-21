package ru.skypro.homework.dto;

import java.util.Objects;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class CreateOrUpdateAdDto {

    private String title;

    private Integer price;

    private String description;

    public CreateOrUpdateAdDto title(String title) {
        this.title = title;
        return this;
    }

    /**
     * заголовок объявления
     *
     * @return title
     */
    @Size(min = 4, max = 32)
    @Schema(name = "title", description = "заголовок объявления", required = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CreateOrUpdateAdDto price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * цена объявления
     * minimum: 0
     * maximum: 10000000
     *
     * @return price
     */
    @Min(0)
    @Max(10000000)
    @Schema(name = "price", description = "цена объявления", required = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CreateOrUpdateAdDto description(String description) {
        this.description = description;
        return this;
    }

    /**
     * описание объявления
     *
     * @return description
     */
    @Size(min = 8, max = 64)
    @Schema(name = "description", description = "описание объявления", required = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateOrUpdateAdDto createOrUpdateAdDto = (CreateOrUpdateAdDto) o;
        return Objects.equals(this.title, createOrUpdateAdDto.title) &&
                Objects.equals(this.price, createOrUpdateAdDto.price) &&
                Objects.equals(this.description, createOrUpdateAdDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateOrUpdateAdDto {\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

