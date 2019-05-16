package com.sup2is.allaboutmise.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sup2is.allaboutmise.model.Modes;

public class ModesConverter {
	
	public static List<Map<String, String>> convertToListMap() {
		
		ArrayList<Map<String, String>> modes = new ArrayList<>();
		
		Modes[] ms = Modes.values();
		
		Map<String, String> map;
		for (Modes m : ms) {
			map = new HashMap<>();
			map.put("name", m.getName());
			map.put("value", m.getValue());
			map.put("grade", m.getGrade());
			modes.add(map);
		}

		return modes;
	}

}

