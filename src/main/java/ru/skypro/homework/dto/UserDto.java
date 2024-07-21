package ru.skypro.homework.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class UserDto {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    /**
     * роль пользователя
     */
    public enum RoleEnum {
        USER("USER"),

        ADMIN("ADMIN");

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static RoleEnum fromValue(String value) {
            for (RoleEnum b : RoleEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("role")
    private RoleEnum role;

    @JsonProperty("image")
    private String image;

    public UserDto id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * id пользователя
     *
     * @return id
     */

    @Schema(name = "id", description = "id пользователя", required = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    /**
     * логин пользователя
     *
     * @return email
     */

    @Schema(name = "email", description = "логин пользователя", required = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * имя пользователя
     *
     * @return firstName
     */

    @Schema(name = "firstName", description = "имя пользователя", required = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserDto lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * фамилия пользователя
     *
     * @return lastName
     */

    @Schema(name = "lastName", description = "фамилия пользователя", required = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserDto phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * телефон пользователя
     *
     * @return phone
     */

    @Schema(name = "phone", description = "телефон пользователя", required = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDto role(RoleEnum role) {
        this.role = role;
        return this;
    }

    /**
     * роль пользователя
     *
     * @return role
     */

    @Schema(name = "role", description = "роль пользователя", required = false)
    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public UserDto image(String image) {
        this.image = image;
        return this;
    }

    /**
     * ссылка на аватар пользователя
     *
     * @return image
     */

    @Schema(name = "image", description = "ссылка на аватар пользователя", required = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(this.id, userDto.id) &&
                Objects.equals(this.email, userDto.email) &&
                Objects.equals(this.firstName, userDto.firstName) &&
                Objects.equals(this.lastName, userDto.lastName) &&
                Objects.equals(this.phone, userDto.phone) &&
                Objects.equals(this.role, userDto.role) &&
                Objects.equals(this.image, userDto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, phone, role, image);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDto {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    role: ").append(toIndentedString(role)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
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

