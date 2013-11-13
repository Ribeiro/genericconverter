package br.com.gigio.genericconverter.implementations;

import br.com.gigio.genericconverter.domain.JPMaps;
import br.com.gigio.genericconverter.interfaces.Converter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ListOfMapsXmlConverter implements Converter<JPMaps, String>{
	 private String name;
	 private XStream xStream;
	 
	 public ListOfMapsXmlConverter(){
		 this.name = ListOfMapsXmlConverter.class.getSimpleName();
		 this.xStream = new XStream(new DomDriver());
		 this.xStream.alias("list", JPMaps.class);
	 }

	public String getName() {
		return this.name;
	}

	public String convert(JPMaps jpMaps) {
	    return  xStream.toXML(jpMaps);
	}

}
