package by.bsu.xmlstudents;

public class RunDOMBuilder {

	public static void main(String[] args) {
		StudentsDOMBuilder domBuilder = new StudentsDOMBuilder();
		domBuilder.buildSetStudents("xml/students.xml");
		System.out.println(domBuilder.getStudents());
	}

}
