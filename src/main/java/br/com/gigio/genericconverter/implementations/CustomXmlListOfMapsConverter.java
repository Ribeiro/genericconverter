package br.com.gigio.genericconverter.implementations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import br.com.gigio.genericconverter.interfaces.Converter;

public class CustomXmlListOfMapsConverter implements Converter<String, Object>{
	
	 private String name;
	 
	 public CustomXmlListOfMapsConverter(){
		 this.name = CustomXmlListOfMapsConverter.class.getSimpleName();
	 }

	@SuppressWarnings("unchecked")
	public Object convert(String xml) {
		 SAXBuilder builder = new SAXBuilder();
		 List<Map<String, Object>> resultingListOfMaps = new ArrayList<Map<String, Object>>();
		 
		 InputStream inputStream = IOUtils.toInputStream(xml);
		 
		  try {
			
			Document document = (Document) builder.build(inputStream);
			Element rootNode = document.getRootElement();
			List<Element> rootChildren = rootNode.getChildren();
			Integer rootChildrenSize = rootChildren.size();
			
			for (int i = 0; i < rootChildrenSize; i++) {
				resultingListOfMaps.add(new HashMap<String, Object>());
				
				Element node = rootChildren.get(i);
				List<Element> nodeChildren = node.getChildren();
				Integer nodeChildrenSize = nodeChildren.size();
				
				for (int j = 0; j < nodeChildrenSize; j++) {
					Element nodeChild = nodeChildren.get(j);
					resultingListOfMaps.get(i).put(nodeChild.getName(), nodeChild.getContent(0).getValue());
				}
				
			}
	 
		  } catch (JDOMException e) {
			System.out.println(e.getMessage());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		} finally {
			  IOUtils.closeQuietly(inputStream);
			  
		}
		  
		  return resultingListOfMaps;
	}

	public String getName() {
		return this.name;
	}

}