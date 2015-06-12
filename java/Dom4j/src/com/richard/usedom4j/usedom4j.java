package com.richard.usedom4j;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class usedom4j {

	public static void main(String[] args) throws DocumentException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book1.xml"));
		Element root = document.getRootElement();
		Element bookname = root.element("书").element("书名");
		System.out.println(bookname.getText());
	}

}
