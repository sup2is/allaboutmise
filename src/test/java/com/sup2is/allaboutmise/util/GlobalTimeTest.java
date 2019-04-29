package com.sup2is.allaboutmise.util;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class GlobalTimeTest {

	@Test
	public void testGetCurrentTimeToSeconds() {
		
		long currentTimeSeconds = System.currentTimeMillis() / 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(sdf.format(new Date(currentTimeSeconds)));
		//오차를 감안해서 5분 5초마다 페이지 갱신
		long reloadTime = (currentTimeSeconds % 300) + 5;
		
		System.out.println(reloadTime);
		System.out.println(currentTimeSeconds);
		System.out.println(reloadTime - currentTimeSeconds + " : " + currentTimeSeconds);
		
	}
	
	@Test 
	public void testGetGlobalTimeToSeconds() {
		System.out.println(GlobalTime.getReloadTimeToSeconds());
	}
	
	

}
