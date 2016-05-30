package by.bsu.xmlstudents;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StudentsDOMBuilder {
	private Set<Student> students;
	private DocumentBuilder docBuilder;
	
	public StudentsDOMBuilder() {
		this.students = new HashSet<Student>();
		// �������� DOM-�����������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("������ ������������ �������: " + e);
		}
	}
	
	public Set<Student> getStudents() {
		return students;
	}
	
	public void buildSetStudents(String fileName) {
		Document doc = null;
		try {
			// ������� XML-��������� � �������� ����������� ���������
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			
			// ��������� ������ �������� ��������� <student>
			NodeList studentsList = root.getElementsByTagName("student");
			for (int i = 0; i < studentsList.getLength(); i++) {
				Element studentElement = (Element) studentsList.item(i);
				Student student = buildStudent(studentElement);
				students.add(student);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error : " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure : " + e);
		}
	}
	
	private Student buildStudent(Element studentElement) {
		Student student = new Student();
		
		// ���������� ������� student
		student.setFaculty(studentElement.getAttribute("faculty"));
		student.setName(getElementTextContent(studentElement, "name"));
		Integer tel = Integer.parseInt(getElementTextContent(studentElement, "telephone"));
		student.setTelephone(tel);
		
		Student.Address address = student.getAddress();
		// ���������� ������� address
		Element addressElement = (Element) studentElement.getElementsByTagName("address").item(0);
		address.setCountry(getElementTextContent(addressElement, "country"));
		address.setCity(getElementTextContent(addressElement, "city"));
		address.setStreet(getElementTextContent(addressElement, "street"));
		
		student.setLogin(studentElement.getAttribute("login"));
		
		return student;
	}
	
	// ��������� ���������� ����������� ����
	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
