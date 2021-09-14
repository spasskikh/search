package com.overonix.search.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultDto {

    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;

}
