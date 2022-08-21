package ChainOfResponsibility;

import Interface.Transport;

import java.io.IOException;

public interface ChainOfResponsibility {

    void print(Transport transport) throws IOException;
    void setNextTransport(ChainOfResponsibility nextTransport);
}
