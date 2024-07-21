package ru.skypro.homework.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class AdDto {

    private Integer author;

    private String image;

    private Integer pk;

    private Integer price;

    private String title;

    public AdDto author(Integer author) {
        this.author = author;
        return this;
    }

    /**
     * id автора объявления
     *
     * @return author
     */

    @Schema(name = "author", description = "id автора объявления", required = false)
    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public AdDto image(String image) {
        this.image = image;
        return this;
    }

    /**
     * ссылка на картинку объявления
     *
     * @return image
     */

    @Schema(name = "image", description = "ссылка на картинку объявления", required = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AdDto pk(Integer pk) {
        this.pk = pk;
        return this;
    }

    /**
     * id объявления
     *
     * @return pk
     */

    @Schema(name = "pk", description = "id объявления", required = false)
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public AdDto price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * цена объявления
     *
     * @return price
     */

    @Schema(name = "price", description = "цена объявления", required = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public AdDto title(String title) {
        this.title = title;
        return this;
    }

    /**
     * заголовок объявления
     *
     * @return title
     */

    @Schema(name = "title", description = "заголовок объявления", required = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdDto adDto = (AdDto) o;
        return Objects.equals(this.author, adDto.author) &&
                Objects.equals(this.image, adDto.image) &&
                Objects.equals(this.pk, adDto.pk) &&
                Objects.equals(this.price, adDto.price) &&
                Objects.equals(this.title, adDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AdDto {\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

