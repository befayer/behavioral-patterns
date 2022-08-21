import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;

public class StudentHandler extends DefaultHandler {

    private Student student;
    private final LinkedList<Subject> subjects = new LinkedList<>();

    boolean isAverage = false;

    public Student getStudent() {
        Subject[] subjectsArray = new Subject[subjects.size()];
        subjects.toArray(subjectsArray);
        student.setSubjects(subjectsArray);
        return student;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            student = new Student();
            student.setLastname(attributes.getValue("lastname"));
        } else if (qName.equalsIgnoreCase("subject")) {
            Subject subject = new Subject();
            subject.setTitle(attributes.getValue("title"));
            subject.setMark(Integer.parseInt(attributes.getValue("mark")));
            subjects.add(subject);
        } else if (qName.equalsIgnoreCase("average")) {
            isAverage = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (isAverage) {
            String avg = new String(ch, start, length);
            student.setAverageOfMarks(Double.parseDouble(avg));
            isAverage = false;
        }
    }
}
