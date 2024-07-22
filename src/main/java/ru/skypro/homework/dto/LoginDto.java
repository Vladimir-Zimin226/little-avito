package ru.skypro.homework.dto;

import java.util.Objects;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class LoginDto {

    private String password;

    private String username;

    public LoginDto password(String password) {
        this.password = password;
        return this;
    }

    /**
     * пароль
     *
     * @return password
     */
    @Size(min = 8, max = 16)
    @Schema(name = "password", description = "пароль", required = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDto username(String username) {
        this.username = username;
        return this;
    }

    /**
     * логин
     *
     * @return username
     */
    @Size(min = 4, max = 32)
    @Schema(name = "username", description = "логин", required = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginDto loginDto = (LoginDto) o;
        return Objects.equals(this.password, loginDto.password) &&
                Objects.equals(this.username, loginDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LoginDto {\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
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

