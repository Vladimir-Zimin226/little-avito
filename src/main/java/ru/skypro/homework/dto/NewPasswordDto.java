package ru.skypro.homework.dto;

import java.util.Objects;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class NewPasswordDto {

    private String currentPassword;

    private String newPassword;

    public NewPasswordDto currentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }

    /**
     * текущий пароль
     *
     * @return currentPassword
     */
    @Size(min = 8, max = 16)
    @Schema(name = "currentPassword", description = "текущий пароль", required = false)
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public NewPasswordDto newPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    /**
     * новый пароль
     *
     * @return newPassword
     */
    @Size(min = 8, max = 16)
    @Schema(name = "newPassword", description = "новый пароль", required = false)
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewPasswordDto newPasswordDto = (NewPasswordDto) o;
        return Objects.equals(this.currentPassword, newPasswordDto.currentPassword) &&
                Objects.equals(this.newPassword, newPasswordDto.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPassword, newPassword);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NewPasswordDto {\n");
        sb.append("    currentPassword: ").append(toIndentedString(currentPassword)).append("\n");
        sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
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

