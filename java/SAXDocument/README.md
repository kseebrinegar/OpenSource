Anthor:richardgong
#Use java SAX to parse XML
if you wanna parser xml in use SAX

we need four java Object

*  1.Use abstrat SAXParserFactory's newInstance method to create SAXParserFactory instance
*  2.Use SAXParserFactory's newSAXParser method to create SAXParser instance
*  3.Use SAXParser's getXMLReader methos to create XMLReader instance
*  4.Implement ContentHandler interface
*  5.Pass ContentHandler implement object as argument to XMLReader's setContentHandler method
*  6.Reader  XML


### main enter
````java
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser sp = factory.newSAXParser();
    XMLReader reader = sp.getXMLReader();
    reader.setContentHandler(new MycontentHandler());
    reader.parse("src/book1.xml");
````

### Implement ContentHandler interface

```java

class MycontentHandler implements ContentHandler {
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {

		System.out.println("corrent tagName:" + qName + ",start element");

		// get attrs
		for (int i = 0; i < atts.getLength(); i++) {

			System.out.println("attrsName:" + atts.getQName(i) + "   value:"
					+ atts.getValue(i));
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("end element" + qName);

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("current element content"
				+ new String(ch, start, length));

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

```



That's ok