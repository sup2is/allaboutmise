package com.sup2is.allaboutmise.util;

public class GlobalTime {
	
/**
 * @return 5분 +5초간격으로 페이징 로드 만약 현재시간이 12분 36초라면 return되는 값은 2분 36초 -> 166초 + 5초 = 171초
 */
	public static long getReloadTimeToSeconds() {
		return System.currentTimeMillis() / 1000 % 300 + 5;
	}
	
}
