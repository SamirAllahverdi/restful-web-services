package com.example.controller;


import com.example.model.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class FilteringController {



    @GetMapping("/filter")
    public MappingJacksonValue getWithFilter() {

        SomeBean object = new SomeBean("value1", "value2", "value3");

        return mappingToJacksonValue(object,"field1","field3");
    }

    @GetMapping("/filter-list")
    public MappingJacksonValue getWithFilterList() {

        List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));

        return mappingToJacksonValue(someBeanList,"field2");
    }

    private MappingJacksonValue mappingToJacksonValue(Object obj, String ... fields) {

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(obj);
        mapping.setFilters(filters);

        return mapping;
    }

}
