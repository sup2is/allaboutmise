package com.sup2is.allaboutmise.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GradeTest {

	@Test
	public void testGetCssClass() {
		
		//given
		Mise mise = new Mise();
		mise.setPm10Value("10");
		
		//when
		String cssClass = Pm10ValueGrade.getPm10ValueCssClass(Integer.parseInt(mise.getPm10Value()));
		
		//then
		assertEquals("primary", cssClass);
		
	}

}
