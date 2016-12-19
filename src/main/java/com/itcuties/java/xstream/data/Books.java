package com.itcuties.java.xstream.data;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Books {

	@XStreamImplicit(itemFieldName = "book")
	private List<Book> books;
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		for(Book b:books)
		{
		sb.append(""+b);
		}
						
		return sb.toString(); 
	}




}
