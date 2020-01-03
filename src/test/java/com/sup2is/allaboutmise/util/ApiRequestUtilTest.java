package com.sup2is.allaboutmise.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiRequestUtilTest {

    @Autowired ApiRequestUtil apiRequestUtil;

    @Test
    public void ApiUrl생성() throws URISyntaxException {
        URI url = apiRequestUtil.apiRequestDto.createBaseApiRequestUrl("서울");
        assertNotNull(url);
        System.out.println("===================");
        System.out.println(url);
    }

    @Test
    public void Api요청테스트() throws URISyntaxException {
        ResponseEntity<String> response = apiRequestUtil.requestAirPollutionData("서울");
        assertEquals(HttpStatus.OK , response.getStatusCode());
    }
}
