package com.example.model;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;


@JsonFilter("SomeBeanFilter")
@Data
@AllArgsConstructor
public class SomeBean {

    private String field1;
    private String field2;
    private String field3;

}
