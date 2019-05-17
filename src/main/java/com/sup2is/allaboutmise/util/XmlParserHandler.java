package com.sup2is.allaboutmise.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sup2is.allaboutmise.model.Mise;

@Component
public class XmlParserHandler extends DefaultHandler {	
	
	private static StringBuilder builder = new StringBuilder(); 
	private static Field[] fields = Mise.class.getDeclaredFields();
	private List<Mise> miseList;
	private Mise mise;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(builder.length() > 0) {
			builder.setLength(0);
		}
		builder.append(qName);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("item")) {
			if(miseList == null) {
				miseList = new ArrayList<>();
			}
			miseList.add(mise);
			mise = null;
		}
		builder.setLength(0);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(builder.toString().equals("item")) {
			mise = new Mise();
		}
		
		if(mise != null) {
			setDataFromFieldName(builder.toString(), new String(ch,start,length));
		}
	}
	
	public List<Mise> getParsedData() {
		List<Mise> copy = new ArrayList<Mise>();
		copy.addAll(miseList);
		miseList.clear();
		return copy;
	}

	private void setDataFromFieldName(String name, String value) {
		if(value.equals("-")) {
			value = "0";
		}
		try {
			for(Field field : fields) {
				if(field.getName().equals(name)) {
					field.setAccessible(true);
					field.set(mise, value);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
