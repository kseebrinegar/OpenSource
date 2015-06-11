package com.richard.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class sax {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser sp = factory.newSAXParser();
		XMLReader reader = sp.getXMLReader();
		reader.setContentHandler(new MycontentHandler());
		reader.parse("src/book1.xml");
		
	}

}

class  MycontentHandler implements ContentHandler{
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {

		System.out.println("corrent tagName:"+qName+",start element");
		
		//get attrs
		for (int i = 0; i < atts.getLength(); i++) {
			
			System.out.println("attrsName:"+atts.getQName(i)+"   value:"+atts.getValue(i));
		}
		
		
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("end element"+qName);
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("current element content"+new String(ch,start,length));
	
	}
	
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}