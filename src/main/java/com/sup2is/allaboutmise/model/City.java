package com.sup2is.allaboutmise.model;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum City {

	SEOUL("서울"),
	BUSAN("부산"),
	DAEGU("대구"),
	INCHON("인천"),
	GWANGJU("광주"),
	DAEJEON("대전"),
	ULSAN("울산"),
	GYEONGGI("경기"),
	GANGWON("강원"),
	CHUNGBUK("충북"),
	CHUNGNAM("충남"),
	JEONBUK("전북"),
	JEONNAM("전남"),
	GYEONGBUK("경북"),
	GYEONGNAM("경남"),
	JEJU("제주"),
	SEJONG("세종");

	private String name;

	City(String name) {
		this.name = name;
	}

	public static List<City> getCities() {
		return Arrays.stream(City.values())
				.collect(toList());
	}

	public String getName() {
		return this.name;
	}

}