package com.itcuties.java.xstream.data;

import java.io.ObjectStreamException;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * More complex example. It is a summary of already showed issues. It also shows
 * several additional things: <li>transient field modifier will cause the field
 * NOT to be loaded even if annotated and present in xml <li>constructor is not
 * being invoked <li>default values given to fields will be replaced if the
 * fields are present in xml file <li>how to force xstream to set a default
 * value for an attribute if it is not present in xml
 * 
 * @author itcuties
 * 
 */
@XStreamAlias("complex-example")
public class ComplexExample {
	
	
	@XStreamAlias("number-1")
	private int number1 = 9;// default value will be replaced if present in the xml

	@XStreamAlias("number-2")
	private int number2 = 12;// default value will be erased if NOT present in xml (in this case it will be set to 0)

	@XStreamAlias("number-3")
	private Short number3;// any numeric type will do (Object or primitive) just make sure the number value will fit

	@XStreamAlias("attribute")
	@XStreamAsAttribute
	private String attribute = "Quake 3 rules";

	@XStreamAlias("default-attribute")
	@XStreamAsAttribute
	private String defaultAttribute;

	@XStreamAlias("transientString")
	private transient String transientString;// this will not be read because it
												// is transient

	@XStreamImplicit(itemFieldName = "data")
	private List<String> data;

	@XStreamAlias("books")
	private Books books;	
	
	//@XStreamImplicit(itemFieldName = "book")
	//private List<Book> books;

//	@XStreamAlias("book")
//	private Book book; 
	
	@Override
	public String toString() {
		return "ComplexExample [\nnumber1=" + number1 + "\n, number2=" + number2
				+ "\n, number3=" + number3 + "\n, attribute=" + attribute
				+ "\n, defaultAttribute=" + defaultAttribute
				+ "\n, transientString=" + transientString + "\n, data=" + data
				+ "\n, books=" + books + "]";
	}

	/**
	 * There is one major flaw in XStream. Unfortunately it has no way of
	 * telling if a field or attribute should get any default value if not
	 * present in the xml file. Because constructor is not being invoked we
	 * cannot set the value there. Neither setting the value in field definition
	 * will work. The resulting instance will always have zero or null values in
	 * the fields.
	 * 
	 * The only way of setting the desired default value is using the following
	 * method. It is called during deserialization process and here we can check
	 * if the field value is null. If yes it means that it's tag is not present
	 * and we can set the default value if needed.
	 * 
	 * @return this
	 * @throws ObjectStreamException
	 */
	private/* or public or protected or none */Object readResolve()
			throws ObjectStreamException {
		if (defaultAttribute == null) {
			defaultAttribute = "DEFAULT ATTRIBUTE VALUE";
		}
		return this;
	}
}
