package com.sup2is.allaboutmise.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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
	public void testGetParsedData() {
		List<Mise> mise = handler.getParsedData();
		assertEquals(10, mise.size());
		assertEquals("신흥", mise.get(0).getStationName());
	}
	
	@Ignore
	@Test
	public void testSetDataFromFieldName() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = XmlParserHandler.class.getDeclaredMethod("setDataFromFieldName",Mise.class, String.class, String.class);
		m.setAccessible(true);
		String name = "stationName";
		String testValue = "중구";
		m.invoke(new XmlParserHandler(), new Mise(), name,testValue);
	}

}

