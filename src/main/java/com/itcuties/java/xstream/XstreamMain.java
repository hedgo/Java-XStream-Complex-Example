package com.itcuties.java.xstream;

import java.io.File;

import com.itcuties.java.xstream.data.ComplexExample;
import com.thoughtworks.xstream.XStream;


public class XstreamMain {

	public static void main(String[] args) {
		try {
			
		//z XML do obiekt
			XStream xStream = new XStream();
			xStream.processAnnotations(ComplexExample.class);
			Object readObject = xStream.fromXML(new File("xmls/complex-example.xml"));
			System.out.println("Object loaded by xstream: " + readObject);

		//z obiekt do XML
			ComplexExample ce = new ComplexExample();
			String s = xStream.toXML(ce);
			System.out.println("XML produced by xstream: \n" + s);
			
		} catch (Exception e) {
			System.err.println("This exception should not happen: "	+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
