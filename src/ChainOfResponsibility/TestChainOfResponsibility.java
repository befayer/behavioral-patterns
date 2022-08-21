package ChainOfResponsibility;

import Interface.Transport;
import Interface.TransportFactory;
import Source.Static;

import java.io.IOException;

public class TestChainOfResponsibility {

    public static void main(String[] args) throws IOException {

        ChainOfResponsibility startHandler = new LinePrint();
        startHandler.setNextTransport(new ColumnPrint());
        Transport testTransportForLineOutput = Static.createInstance("Two brands", 2);
        Transport testTransportForColumnOutput = Static.createInstance("Five brands", 5);
        startHandler.print(testTransportForLineOutput);
        startHandler.print(testTransportForColumnOutput);
    }
}
