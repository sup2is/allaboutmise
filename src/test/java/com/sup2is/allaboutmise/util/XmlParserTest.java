package com.sup2is.allaboutmise.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.sup2is.allaboutmise.model.Mise;

public class XmlParserTest {
	
	private XmlParserHandler handler;
	
	@Before
	public void setup() throws SAXException, IOException, ParserConfigurationException {
		URL fileUrl = getClass().getResource("/mise-test.xml");
		File file = new File(fileUrl.getFile());
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		handler = new XmlParserHandler();
		parser.parse(file, handler);
	}
	
	@Test
	public void Xml테스트데이터파싱() {
		List<Mise> mise = handler.getParsedData();
		assertEquals(10, mise.size());
		assertEquals("신흥", mise.get(0).getStationName());
		System.out.println(mise.toString());
	}

}

