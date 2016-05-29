package by.bsu.jaxb;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import by.bsu.xmlstudents.Students;

public class UnMarshallMain {
	public static void main(String[] args) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Students.class);
			Unmarshaller u = jc.createUnmarshaller();
			FileReader reader = new FileReader("xml/studs_marsh.xml");
			Students students = (Students) u.unmarshal(reader);
			System.out.println(students);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
