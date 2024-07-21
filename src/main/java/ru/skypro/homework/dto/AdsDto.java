package ru.skypro.homework.dto;

import java.util.Objects;

import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
public class AdsDto {

    private Integer count;

    @Valid
    private List<AdDto> results = null;

    public AdsDto count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * общее количество объявлений
     *
     * @return count
     */

    @Schema(name = "count", description = "общее количество объявлений", required = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AdsDto results(List<AdDto> results) {
        this.results = results;
        return this;
    }

    public AdsDto addResultsItem(AdDto resultsItem) {
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
    public List<AdDto> getResults() {
        return results;
    }

    public void setResults(List<AdDto> results) {
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
        AdsDto adsDto = (AdsDto) o;
        return Objects.equals(this.count, adsDto.count) &&
                Objects.equals(this.results, adsDto.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AdsDto {\n");
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

