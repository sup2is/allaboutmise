package com.sup2is.allaboutmise;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class AllaboutmiseApplicationTests {

	@Autowired
	private Environment env;
	
	@Autowired
	private StandardPBEStringEncryptor encryptor;
	
	@Test
	public void test_jasypt_프로퍼티_복호화_체크() {
		
		System.out.println(encryptor.decrypt(env.getProperty("app.service.key")));
		
	}

}
