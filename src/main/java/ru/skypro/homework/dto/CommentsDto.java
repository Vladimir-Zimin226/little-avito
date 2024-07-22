package ru.skypro.homework.dto;

import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class CommentsDto {

    private Integer count;

    @Valid
    private List<CommentDto> results = null;

    public CommentsDto count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * общее количество комментариев
     *
     * @return count
     */

    @Schema(name = "count", description = "общее количество комментариев", required = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CommentsDto results(List<CommentDto> results) {
        this.results = results;
        return this;
    }

    public CommentsDto addResultsItem(CommentDto resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     *
     * @return results
     */
    @Valid
    @Schema(name = "results", required = false)
    public List<CommentDto> getResults() {
        return results;
    }

    public void setResults(List<CommentDto> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentsDto commentsDto = (CommentsDto) o;
        return Objects.equals(this.count, commentsDto.count) &&
                Objects.equals(this.results, commentsDto.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CommentsDto {\n");
        sb.append("    count: ").append(toIndentedString(count)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
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

