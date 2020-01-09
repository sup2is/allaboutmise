package com.sup2is.allaboutmise.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.service.MiseService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalTimeTest {

	@Autowired
	MiseService miseService;

	@Autowired
	GlobalTime globalTime;

	@Ignore
	@Test
	public void 전역시간테스트_5초() throws Exception {

		String city = "서울";
		List<Mise> pollutionData = miseService.getCachedRealTimeMiseListByCityName(city);
		/*
			5초 sleep
			실제 encache cache time이 3595인것을 감안해야함
			5초는 5000 ms 이기 때문에 + 5000ms 해줘야함
		 */
		long start = System.currentTimeMillis();
		TimeUnit.SECONDS.sleep(5);
		long end = System.currentTimeMillis() + 5000;

		//when
		int reloadTimeToSeconds = globalTime.getReloadTimeToSeconds(city);

		//then
		System.out.println("reloadTimeToSeconds : " + reloadTimeToSeconds);
		System.out.println("(end - start) / 1000 : " + (end - start) / 1000 + 1);
		assertTrue(reloadTimeToSeconds <= (end - start / 1000) + 1);
	}

	@Test
	@Ignore
	public void 전역시간테스트_20초() throws Exception {

		String city = "서울";
		List<Mise> pollutionData = miseService.getCachedRealTimeMiseListByCityName(city);
		/*
			5초 sleep
			실제 encache cache time이 3595인것을 감안해야함
			5초는 5000 ms 이기 때문에 + 5000ms 해줘야함
		 */
		long start = System.currentTimeMillis();
		TimeUnit.SECONDS.sleep(20);
		long end = System.currentTimeMillis() + 5000;

		//when
		int reloadTimeToSeconds = globalTime.getReloadTimeToSeconds(city);

		//then
		System.out.println("reloadTimeToSeconds : " + reloadTimeToSeconds);
		System.out.println("(end - start) / 1000 : " + (end - start) / 1000 + 1);
		assertTrue(reloadTimeToSeconds <= (end - start / 1000) + 1);
	}
}