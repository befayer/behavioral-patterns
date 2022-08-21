import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SAX implements AnalizationAverageStrategy{

    @Override
    public void rewriteFile(String inputFileName, String outputFileName) {
        try {
            Student student = readStudent(inputFileName);
            student.setNewValueForAverageOfMarks();
            writeStudent(student, outputFileName);
        } catch (ParserConfigurationException | SAXException | IOException | XMLStreamException ex) {
            Logger.getLogger(SAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Student readStudent(String inputFileName) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        StudentHandler userhandler = new StudentHandler();
        saxParser.parse(inputFileName, userhandler);
        return userhandler.getStudent();
    }

    private void writeStudent(Student student, String outputFileName) throws XMLStreamException, IOException {
        String xmlString;
        try (StringWriter stringWriter = new StringWriter()) {
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

            xMLStreamWriter.writeStartDocument();

            xMLStreamWriter.writeStartElement("student");
            xMLStreamWriter.writeAttribute("lastname", student.getLastname());

            for (Subject subject : student.getSubjects()) {
                xMLStreamWriter.writeEmptyElement("subject");
                xMLStreamWriter.writeAttribute("mark", String.valueOf(subject.getMark()));
                xMLStreamWriter.writeAttribute("title", subject.getTitle());
            }

            xMLStreamWriter.writeStartElement("average");
            xMLStreamWriter.writeCharacters(String.valueOf(student.getAverageOfMarks()));
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            xmlString = stringWriter.getBuffer().toString();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(xmlString);
        }
    }
}
