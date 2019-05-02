package com.sup2is.allaboutmise.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * 
 * 미세먼지농도는 pm10Value 로 결정
 * 10~15 좋음 , 16~35 보통
 * 36~75 나쁨 76이상 매우나쁨
 * 100이상 외출금지
 * @author CHS
 *
 */
@Getter
@Setter
@ToString
public class Mise {
	
	private String stationName;
	private String mangName;
	private String dataTime;
	private String so2Value;
	private String coValue;
	private String o3Value;
	private String no2Value;
	private String pm10Value;
	private String pm10Value24;
	private String pm25Value;
	private String pm25Value24;
	private String khai;
	private String khaiGrade;
	private String so2Grade;
	private String coGrade;
	private String o3Grade;
	private String no2Grade;
	private String pm10Grade;
	private String pm25Grade;
	private String pm10Grade1H;
	private String pm25Grade1H;
	private String cssClass;
	private boolean show = false;
	
}
