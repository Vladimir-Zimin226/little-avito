package ru.skypro.homework.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class ExtendedAdDto {

    private Integer pk;

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    private String image;

    private String phone;

    private Integer price;

    private String title;

    public ExtendedAdDto pk(Integer pk) {
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

    public ExtendedAdDto authorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    /**
     * имя автора объявления
     *
     * @return authorFirstName
     */

    @Schema(name = "authorFirstName", description = "имя автора объявления", required = false)
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public ExtendedAdDto authorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    /**
     * фамилия автора объявления
     *
     * @return authorLastName
     */

    @Schema(name = "authorLastName", description = "фамилия автора объявления", required = false)
    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public ExtendedAdDto description(String description) {
        this.description = description;
        return this;
    }

    /**
     * описание объявления
     *
     * @return description
     */

    @Schema(name = "description", description = "описание объявления", required = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExtendedAdDto email(String email) {
        this.email = email;
        return this;
    }

    /**
     * логин автора объявления
     *
     * @return email
     */

    @Schema(name = "email", description = "логин автора объявления", required = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExtendedAdDto image(String image) {
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

    public ExtendedAdDto phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * телефон автора объявления
     *
     * @return phone
     */

    @Schema(name = "phone", description = "телефон автора объявления", required = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ExtendedAdDto price(Integer price) {
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

    public ExtendedAdDto title(String title) {
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
        ExtendedAdDto extendedAdDto = (ExtendedAdDto) o;
        return Objects.equals(this.pk, extendedAdDto.pk) &&
                Objects.equals(this.authorFirstName, extendedAdDto.authorFirstName) &&
                Objects.equals(this.authorLastName, extendedAdDto.authorLastName) &&
                Objects.equals(this.description, extendedAdDto.description) &&
                Objects.equals(this.email, extendedAdDto.email) &&
                Objects.equals(this.image, extendedAdDto.image) &&
                Objects.equals(this.phone, extendedAdDto.phone) &&
                Objects.equals(this.price, extendedAdDto.price) &&
                Objects.equals(this.title, extendedAdDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, authorFirstName, authorLastName, description, email, image, phone, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExtendedAdDto {\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
        sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
        sb.append("    authorLastName: ").append(toIndentedString(authorLastName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

