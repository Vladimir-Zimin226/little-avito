package ru.skypro.homework.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class CommentDto {

    private Integer author;

    private String authorImage;

    private String authorFirstName;

    private Long createdAt;

    private Integer pk;

    private String text;

    public CommentDto author(Integer author) {
        this.author = author;
        return this;
    }

    /**
     * id автора комментария
     *
     * @return author
     */

    @Schema(name = "author", description = "id автора комментария", required = false)
    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public CommentDto authorImage(String authorImage) {
        this.authorImage = authorImage;
        return this;
    }

    /**
     * ссылка на аватар автора комментария
     *
     * @return authorImage
     */

    @Schema(name = "authorImage", description = "ссылка на аватар автора комментария", required = false)
    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public CommentDto authorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    /**
     * имя создателя комментария
     *
     * @return authorFirstName
     */

    @Schema(name = "authorFirstName", description = "имя создателя комментария", required = false)
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public CommentDto createdAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     *
     * @return createdAt
     */

    @Schema(name = "createdAt", description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970", required = false)
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public CommentDto pk(Integer pk) {
        this.pk = pk;
        return this;
    }

    /**
     * id комментария
     *
     * @return pk
     */

    @Schema(name = "pk", description = "id комментария", required = false)
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public CommentDto text(String text) {
        this.text = text;
        return this;
    }

    /**
     * текст комментария
     *
     * @return text
     */

    @Schema(name = "text", description = "текст комментария", required = false)
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
        CommentDto commentDto = (CommentDto) o;
        return Objects.equals(this.author, commentDto.author) &&
                Objects.equals(this.authorImage, commentDto.authorImage) &&
                Objects.equals(this.authorFirstName, commentDto.authorFirstName) &&
                Objects.equals(this.createdAt, commentDto.createdAt) &&
                Objects.equals(this.pk, commentDto.pk) &&
                Objects.equals(this.text, commentDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CommentDto {\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    authorImage: ").append(toIndentedString(authorImage)).append("\n");
        sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
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

