package br.com.gigio.genericconverter.implementations;

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import br.com.gigio.genericconverter.domain.JPMaps;
import br.com.gigio.genericconverter.enums.ConverterFactory;
import br.com.gigio.genericconverter.interfaces.Converter;

public class ListOfMapsXmlConverterTest {
	
	private ConverterFactory converterFactory;  
    private Converter<JPMaps, String> converterFromListOfMapsToXml;
    private static final String BASE_DIR = "files/listOfMapsXmlConverterTest/";
	
	@Before
	public void setUp(){
		converterFactory = ConverterFactory.getInstance ( );
		converterFromListOfMapsToXml = converterFactory.getConverter(JPMaps.class, String.class);
	}
	
	@Test
	public void shouldSuccessfullyConvertListOfMapsToXml() throws IOException{
		String xml = converterFromListOfMapsToXml.convert(buildListOfMaps());
		String expectedResult = readFile("expected_result.xml");
		assertThat(xml, equalToIgnoringWhiteSpace(expectedResult));
	}
	
	private JPMaps buildListOfMaps(){
		 List<Map<String, Object>> payload = new ArrayList<Map<String, Object>>();
	 		payload.add(new HashMap<String, Object>());
	 		payload.add(new HashMap<String, Object>());
	 		payload.get(0).put("ID", 123);
	 		payload.get(0).put("DEST", "GEOVANNY");
	 		payload.get(0).put("VALOR", 10000.00);
	 		payload.get(1).put("ID", 456);
	 		payload.get(1).put("DEST", "CLAUDEMIRTON");
	 		payload.get(1).put("VALOR", 34000.00);
	 		return new JPMaps(payload);
	}
	
	private static String readFile(String filename) throws IOException {
		return FileUtils.readFileToString(new File(BASE_DIR + filename));
	}

}