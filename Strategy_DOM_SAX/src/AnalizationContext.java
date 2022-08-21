import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class AnalizationContext {

    private AnalizationAverageStrategy strategy = new DOM();

    public void setAnalizationStrategy(AnalizationAverageStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(String inputFileName, String outputFileName) throws ParserConfigurationException, IOException, SAXException {
        strategy.rewriteFile(inputFileName, outputFileName);
    }
}
