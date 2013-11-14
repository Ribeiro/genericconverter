package br.com.gigio.genericconverter.implementations;

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import br.com.gigio.genericconverter.enums.ConverterFactory;
import br.com.gigio.genericconverter.interfaces.Converter;

public class CustomXmlListOfMapsConverterTest {
	
	private ConverterFactory converterFactory;  
    private Converter<String, Object> customConverterFromXMlToListOfMaps;
    private static final String BASE_DIR = "files/customXmlListOfMapsConverterTest/";
    
    @Before
	public void setUp(){
		converterFactory = ConverterFactory.getInstance ( );
		customConverterFromXMlToListOfMaps = converterFactory.getConverter(String.class, Object.class);
	}
    
    @SuppressWarnings("unchecked")
	@Test
	public void shouldSuccessfullyConvertXmlToListOfMaps() throws IOException{
    	Long timeBeforeTest = new Date().getTime();
    	List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) customConverterFromXMlToListOfMaps.convert(readFile("pentaho.xml"));
    	Long timeSpentInConversion = new Date().getTime() - timeBeforeTest;
    	System.out.println("Time spent during conversion : " + timeSpentInConversion);
		assertThat(listOfMaps, hasSize(10000));
		String expectedResult = readFile("expected_result.txt");
		String firsMapOfListAsString = listOfMaps.get(0).toString();
		assertThat(firsMapOfListAsString, equalToIgnoringWhiteSpace(expectedResult));
	}
    
    private static String readFile(String filename) throws IOException {
		return FileUtils.readFileToString(new File(BASE_DIR + filename));
	}

}