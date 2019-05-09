package com.sup2is.allaboutmise.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalTime {
	
/**
 * @return 5분 +5초간격으로 페이징 로드 만약 현재시간이 12분 36초라면 return되는 값은 2분 36초 -> 166초 + 5초 = 171초
 */
	public static long getReloadTimeToSeconds() {
		log.debug("### : getReloadTimeToSeconds call");
		long seconds = System.currentTimeMillis() / 1000;
		log.debug("### : getReloadTimeToSeconds : " + seconds % 300);
		return  seconds % 300 + 5;
	}
	
}
