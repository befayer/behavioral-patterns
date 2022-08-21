import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface AnalizationAverageStrategy {
    void rewriteFile(String inputFile, String outputFile) throws ParserConfigurationException, IOException, SAXException;
}
