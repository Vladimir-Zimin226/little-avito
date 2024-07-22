package ru.skypro.homework.dto;

import java.util.Objects;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class UpdateUserDto {

    private String firstName;

    private String lastName;

    private String phone;

    public UpdateUserDto firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * имя пользователя
     *
     * @return firstName
     */
    @Size(min = 3, max = 10)
    @Schema(name = "firstName", description = "имя пользователя", required = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UpdateUserDto lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * фамилия пользователя
     *
     * @return lastName
     */
    @Size(min = 3, max = 10)
    @Schema(name = "lastName", description = "фамилия пользователя", required = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UpdateUserDto phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * телефон пользователя
     *
     * @return phone
     */
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(name = "phone", description = "телефон пользователя", required = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateUserDto updateUserDto = (UpdateUserDto) o;
        return Objects.equals(this.firstName, updateUserDto.firstName) &&
                Objects.equals(this.lastName, updateUserDto.lastName) &&
                Objects.equals(this.phone, updateUserDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateUserDto {\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

