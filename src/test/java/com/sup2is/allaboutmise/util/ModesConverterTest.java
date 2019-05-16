package com.sup2is.allaboutmise.util;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ModesConverterTest {

	@Test
	public void testConvertToListMap_enum필드들을_전부_list_map형태로_변환() {
		
		//given
		
		//when
		List<Map<String, String>> modes = ModesConverter.convertToListMap();
		
		//then
		assertEquals(9, modes.size());
		
		System.out.println(modes);
	}

}
