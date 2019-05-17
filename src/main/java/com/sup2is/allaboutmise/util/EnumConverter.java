package com.sup2is.allaboutmise.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sup2is.allaboutmise.model.Cities;
import com.sup2is.allaboutmise.model.Modes;

public class EnumConverter {
	
	public static List<Map<String, Object>> modesEnumConvertToListMap() {
		
		ArrayList<Map<String, Object>> modes = new ArrayList<>();
		
		Modes[] ms = Modes.values();
		
		Map<String, Object> map;
		for (Modes m : ms) {
			map = new HashMap<>();
			map.put("name", m.getName());
			map.put("value", m.getValue());
			map.put("grade", m.getGrade());
			map.put("max", (m.getMax() > 1 ? m.getMax() : m.getMax()));
			modes.add(map);
		}

		return modes;
	}
	
	public static List<Map<String, Object>> citiesEnumConvertToListMap() {

		ArrayList<Map<String, Object>> cities = new ArrayList<>();
		
		Cities[] cs = Cities.values();
		
		Map<String, Object> map;
		for (Cities c : cs) {
			map = new HashMap<>();
			map.put("name", c);
			cities.add(map);
		}

		return cities;
		
	}

}

