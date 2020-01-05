package com.sup2is.allaboutmise.service;

import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.util.ApiRequestUtil;
import com.sup2is.allaboutmise.util.XmlParserHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

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
		InputStream is = new ByteArrayInputStream(response.getBody().getBytes("UTF-8"));
		parser.parse(is, xmlParserHandler);
		List<Mise> miseList = xmlParserHandler.getParsedData();
		return miseList;
	}

}
