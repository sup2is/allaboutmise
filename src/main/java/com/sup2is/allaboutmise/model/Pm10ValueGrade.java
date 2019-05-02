package com.sup2is.allaboutmise.model;

public enum Pm10ValueGrade {
	
	AWESOME(0,"primary"),
	GOOD(15,"success"),
	NOTBAD(35,"warning"),
	BAD(75,"danger"),
	TERRIBLE(100,"dark");
	
	int value;
	String cssClass;
	
	Pm10ValueGrade(int value, String cssClass) {
		this.value = value;
		this.cssClass = cssClass;
	}
	
	public static String getPm10ValueCssClass(int i) {
		Pm10ValueGrade grade = AWESOME;
		for (Pm10ValueGrade g : values()) {
			if(g.value <= i) {
				grade = g;
			}
		}
		return grade.cssClass;
	}
	
	
}
