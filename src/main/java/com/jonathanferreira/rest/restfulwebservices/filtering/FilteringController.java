package com.jonathanferreira.rest.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomething() {
        var someClass = new SomeClass("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");

        FilterProvider filters = new SimpleFilterProvider().addFilter("someClassFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someClass);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomething(){
        var list = Arrays.asList(new SomeClass("value1", "value2", "value3"), new SomeClass("value4", "value5", "value6"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("someClassFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }
}
