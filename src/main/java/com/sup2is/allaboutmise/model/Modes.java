package com.sup2is.allaboutmise.model;

public enum Modes {
	PM10("미세먼지(pm10)", "pm10Value", "pm10Grade1h"),
	PM25("미세먼지(pm2.5)", "pm25Value", "pm25Grade1h"),
	PM10_24("미세먼지 24시간 예측농도(pm10)", "pm10Value24", "pm10Grade"),
	PM25_24("미세먼지 24시간 예측농도(pm2.5)", "pm25Value24", "pm25Grade"),
	SO2("아황산가스", "so2Value", "so2Grade"),
	CO("일산화탄소", "coValue", "coGrade"),
	O3("오존", "o3Value", "o3Grade"),
	NO2("이산화질소", "no2Value", "no2Grade"),
	khai("통합대기환경", "khaiValue", "khaiGrade");
	
	private String name;
	private String value;
	private String grade;
	
	Modes(String name, String value, String grade) {
		this.name = name;
		this.value = value;
		this.grade = grade;
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
	
}
