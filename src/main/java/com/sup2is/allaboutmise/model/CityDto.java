package com.sup2is.allaboutmise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CityDto {

    private String name;
    private boolean isActive;

    public static List<CityDto> createCityDtoList(List<City> cities) {
        return cities.stream()
                .map(c -> new CityDto(c.getName(), false))
                .collect(toList());
    }

}
