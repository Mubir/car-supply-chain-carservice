package com.mubir.carservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CarPagedList extends PageImpl<CarDto> implements Serializable {
    static final long serialVersionUID= 8775945117656316501L;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CarPagedList(@JsonProperty("content") List<CarDto> content,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements,
                        @JsonProperty("pageable") JsonNode pageable,
                        @JsonProperty("last") boolean last,
                        @JsonProperty("totalPages") int totalPages,
                        @JsonProperty("sort") JsonNode sort,
                        @JsonProperty("first") boolean first,
                        @JsonProperty("numberOfElements") int numberOfElements)
    {
        super(content, PageRequest.of(number,size),totalElements);
    }
    public CarPagedList(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CarPagedList(List content) {
        super(content);
    }
}
