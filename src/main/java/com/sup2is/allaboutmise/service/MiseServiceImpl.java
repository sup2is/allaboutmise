package com.sup2is.allaboutmise.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.SAXParser;

import com.sup2is.allaboutmise.util.ApiRequestUtil;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.util.XmlParserHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MiseServiceImpl implements MiseService {

	private final XmlParserHandler xmlParserHandler;
	private final SAXParser parser;
	private final StandardPBEStringEncryptor encryptor;
	private final ApiRequestUtil apiRequestUtil;

	@Override
	@Cacheable(value = "mise-realtime", key="#cityName")
	public List<Mise> getCachedRealTimeMiseListByCityName(String cityName) throws Exception {
		ResponseEntity<String> response = apiRequestUtil.requestAirPollutionData(cityName);
		InputStream is = new ByteArrayInputStream(response.getBody().getBytes());
		parser.parse(is, xmlParserHandler);
		List<Mise> miseList = xmlParserHandler.getParsedData();
		System.out.println(miseList);
		return miseList;
	}

}
