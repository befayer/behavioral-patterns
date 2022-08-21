import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;


public class DOM implements AnalizationAverageStrategy{

    @Override
    public void rewriteFile(String inputFile, String outputFile) throws ParserConfigurationException, IOException, SAXException {
        try{
            Student student = readStudent(inputFile);
            student.setNewValueForAverageOfMarks();
            writeStudent(student, outputFile);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public Student readStudent(String inputFileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(inputFileName);
        NodeList studentNodeList, subjectNodeList, averageNodeList;
        Node studentNode, subjectNode, averageNode;
        org.w3c.dom.Element studentElement, subjectElement, averageElement;

        Student student = new Student();
        Subject[] subjects;

        studentNodeList = document.getElementsByTagName("student");

        studentNode = studentNodeList.item(0);
        if (Node.ELEMENT_NODE == studentNode.getNodeType()) {
            studentElement = (org.w3c.dom.Element) studentNode;
            student.setLastname(studentElement.getAttribute("lastname"));

            subjectNodeList = studentElement.getElementsByTagName("subject");
            subjects = new Subject[subjectNodeList.getLength()];
            for (int j = 0; j < subjectNodeList.getLength(); j++) {
                subjectNode = subjectNodeList.item(j);
                if (Node.ELEMENT_NODE == subjectNode.getNodeType()) {
                    subjectElement = (org.w3c.dom.Element) subjectNode;
                    subjects[j] = new Subject();
                    subjects[j].setTitle(subjectElement.getAttribute("title"));
                    subjects[j].setMark(Integer.parseInt(subjectElement.getAttribute("mark")));
                } else {
                    throw new IOException("Student can't be read!");
                }
            }
            student.setSubjects(subjects);
            averageNodeList = studentElement.getElementsByTagName("average");
            averageNode = averageNodeList.item(0);
            if (Node.ELEMENT_NODE == averageNode.getNodeType()) {
                averageElement = (Element) averageNode;
                student.setAverageOfMarks(Double.parseDouble(averageElement.getTextContent()));
            } else {
                throw new IOException("Student can't be read!");
            }
        } else {
            throw new IOException("Student can't be read!");
        }
        return student;
    }

    public void writeStudent(Student student, String outputFileName) throws TransformerException, ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Element studentElement, subjectElement, averageElement;
        Document document1 = documentBuilder.newDocument();
        studentElement = document1.createElement("student");
        studentElement.setAttribute("lastname", student.getLastname());

        for (Subject subject : student.getSubjects()) {
            subjectElement = document1.createElement("subject");
            subjectElement.setAttribute("title", subject.getTitle());
            subjectElement.setAttribute("mark", String.valueOf(subject.getMark()));
            studentElement.appendChild(subjectElement);
        }
        averageElement = document1.createElement("average");
        averageElement.setTextContent(String.valueOf(student.getAverageOfMarks()));
        studentElement.appendChild(averageElement);
        document1.appendChild(studentElement);

        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document1);

        StreamResult result = new StreamResult(outputFileName);
        tr.setOutputProperty(OutputKeys.INDENT, "yes");

        tr.transform(source, result);
    }

}
