package br.com.gigio.genericconverter.implementations;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.com.gigio.genericconverter.domain.JPMaps;
import br.com.gigio.genericconverter.interfaces.Converter;

public class XmlListOfMapsConverter implements Converter<String, JPMaps>{
	 private String name;
	 private XStream xStream;
	 
	 public XmlListOfMapsConverter(){
		 this.name = XmlListOfMapsConverter.class.getSimpleName();
		 this.xStream = new XStream(new DomDriver());
		 this.xStream.alias("list", JPMaps.class);
	 }

	public JPMaps convert(String xml) {
		return (JPMaps) xStream.fromXML(xml);
	}

	public String getName() {
		return this.name;
	}

}