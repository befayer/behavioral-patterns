import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestStrategy {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        if (args.length!=2){
            System.out.println("Неверное количество параметров!");
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        //DOM
        AnalizationContext context = new AnalizationContext();
        context.executeStrategy(inputFileName, outputFileName);

        //SAX
        context.setAnalizationStrategy(new SAX());
        context.executeStrategy(inputFileName, outputFileName);
    }
}
