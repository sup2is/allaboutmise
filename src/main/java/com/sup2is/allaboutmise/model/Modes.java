package com.sup2is.allaboutmise.model;

public enum Modes {
	PM25("미세먼지(pm2.5)", "pm25Value", "pm25Grade1h", 80),
	PM10("미세먼지(pm10)", "pm10Value", "pm10Grade1h", 120),
	PM25_24("미세먼지 24시간 예측농도(pm2.5)", "pm25Value24", "pm25Grade", 60),
	PM10_24("미세먼지 24시간 예측농도(pm10)", "pm10Value24", "pm10Grade", 120),
	SO2("아황산가스(so2)", "so2Value", "so2Grade", 0.15),
	CO("일산화탄소(co)", "coValue", "coGrade", 25),
	O3("오존(o3)", "o3Value", "o3Grade", 0.1),
	NO2("이산화질소(no2)", "no2Value", "no2Grade", 0.13),
	khai("통합대기환경", "khaiValue", "khaiGrade", 200);
	
	private String name;
	private String value;
	private String grade;
	private double max;
	
	Modes(String name, String value, String grade, double max) {
		this.name = name;
		this.value = value;
		this.grade = grade;
		this.max = max;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getGrade() {
		return this.grade;
	}
	public double getMax() {
		return this.max;
	}
	
}
