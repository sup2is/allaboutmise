package com.sup2is.allaboutmise.service;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.util.XmlParserHandler;

@Service
public class MiseServiceImpl implements MiseService {
	
	@Autowired
	private XmlParserHandler xmlParserHandler;
	
	@Autowired
	private SAXParser parser;

	@Override
	@Cacheable(value = "mise", key="#cityName")
	public List<Mise> getCachedMiseListByCityName(String cityName) throws Exception {
		
		//resttemplate 요청
		
		
		//response 가공 (xml)
		
		
		//testData
		URL fileUrl = getClass().getResource("/mise-test.xml");
		File file = new File(fileUrl.getFile());
		
		//parse
		parser.parse(file, xmlParserHandler);
		List<Mise> miseList = xmlParserHandler.getParsedData();
		return miseList;
	}
	
}
