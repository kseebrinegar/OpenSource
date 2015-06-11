#Sax解析器解析XML

if you wanna parser xml SAX

````java
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser sp = factory.newSAXParser();
    XMLReader reader = sp.getXMLReader();
    reader.setContentHandler(new MycontentHandler());
    reader.parse("src/book1.xml");
````


