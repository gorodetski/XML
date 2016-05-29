package by.bsu.jaxb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import by.bsu.xmlstudents.Student;
import by.bsu.xmlstudents.Students;

public class MarshalMain {
	public static void main(String[] args) {
		try {
			JAXBContext content = JAXBContext.newInstance(Students.class);
			Marshaller m = content.createMarshaller();
			Students st = new Students() { // анонимный класс
				{
					// добавление первого студента
					Student.Address addr = new Student.Address("BLR", "Minsk", "Skoriny 4");
					Student s = new Student("gochette", "Klimenko", "mmf", 2095306, addr);
					this.add(s);
					
					// добавление второго студента
					addr = new Student.Address("BLR", "Polotesk", "Simeona P. 23");
					s = new Student("ivette", "Teran", "mmf", 2345386, addr);
					this.add(s);
				}
			};
			m.marshal(st, new FileOutputStream("xml/studs_marsh.xml"));
			m.marshal(st, System.out); // копия на консоль
			System.out.println("XML-файл создан");
		} catch (FileNotFoundException e) {
			System.out.println("XML-файл не может быть создан: " + e);
		} catch (JAXBException e) {
			System.out.println("JAXB-контекст ошибочен: " + e);
		}
	}
}
