package com.sup2is.allaboutmise.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CityDtoTest {

    @Test
    public void CityToCityDto변환() throws Exception{
        //given
        List<CityDto> cities = CityDto.createCityDtoList(City.getCities());

        //when

        //then
        assertNotNull(cities);
        assertEquals(17 , cities.size());
        System.out.println(cities);
    }

}