package by.bsu.valid;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidatorSAXXSD {
	public static void main(String[] args) {
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		String fileName = "xml/students.xml";
		String schemaName = "xml/students.xsd";
		
		SchemaFactory factory = SchemaFactory.newInstance(language);
		File schemaLocation = new File(schemaName);
		
		try {
			// создание схемы
			Schema schema = factory.newSchema(schemaLocation);
			
			// создание валидатора на основе схемы
			Validator validator = schema.newValidator();
			
			// проверка документа
			Source source = new StreamSource(fileName);
			StudentErrorHandler sh = new StudentErrorHandler("logs/log.txt");
			validator.setErrorHandler(sh);
			validator.validate(source);
			System.out.println(fileName + " validating is ended");
		} catch (SAXException e) {
			System.err.println("validation " + fileName + " is not valid because " + e.getMessage());
		} catch (IOException e) {
			System.err.println("validation " + fileName + " is not valid because " + e.getMessage());
		}
	}
}
