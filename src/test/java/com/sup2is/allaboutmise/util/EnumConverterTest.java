package com.sup2is.allaboutmise.util;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class EnumConverterTest {

	@Test
	public void testModesEnumConvertToListMap_modes_enum필드들을_전부_list_map형태로_변환() {
		
		//given
		
		//when
		List<Map<String, Object>> modes = EnumConverter.modesEnumConvertToListMap();
		
		//then
		assertEquals(9, modes.size());
		
		System.out.println(modes);
	}
	
	@Test
	public void testCitiesEnumConvertToListMap_cities_enum필드들을_전부_list_map형태로_변환() {
		
		//given
		
		//when
		List<Map<String, Object>> cities = EnumConverter.citiesEnumConvertToListMap();
		
		//then
		assertEquals(17, cities.size());
		
		System.out.println(cities);
	}
	
	
	

}
