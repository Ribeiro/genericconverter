package br.com.gigio.genericconverter.implementations;

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import br.com.gigio.genericconverter.domain.JPMaps;
import br.com.gigio.genericconverter.enums.ConverterFactory;
import br.com.gigio.genericconverter.interfaces.Converter;

public class XmlListOfMapsConverterTest {
	
	private ConverterFactory converterFactory;  
    private Converter<String, JPMaps> converterFromXMlToListOfMaps;
    private static final String BASE_DIR = "files/xmlListOfMapsConverterTest/";
    
    @Before
	public void setUp(){
		converterFactory = ConverterFactory.getInstance ( );
		converterFromXMlToListOfMaps = converterFactory.getConverter(String.class, JPMaps.class);
	}
    
    @Test
	public void shouldSuccessfullyConvertXmlToListOfMaps() throws IOException{
		JPMaps jpmaps = converterFromXMlToListOfMaps.convert(readFile("testing.xml")) ;
		String expectedResult = readFile("expected_result.txt");
		assertThat(jpmaps.toString(), equalToIgnoringWhiteSpace(expectedResult));
	}
    
    private static String readFile(String filename) throws IOException {
		return FileUtils.readFileToString(new File(BASE_DIR + filename));
	}

}