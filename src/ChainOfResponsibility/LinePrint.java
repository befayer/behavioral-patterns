package ChainOfResponsibility;

import Interface.Transport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinePrint implements ChainOfResponsibility{
    private ChainOfResponsibility nextTransport;

    @Override
    public void print(Transport transport) throws IOException {
        if (transport.getModelArraySize() <= 3) {
            try (PrintWriter printWriter = new PrintWriter("Transport " + transport.getBrand() + ".txt", StandardCharsets.UTF_8)){
                printWriter.print(transport.getBrand() + ": ");
                String[] names = transport.getModelNamesArray();
                double[] prices = transport.getModelPricesArray();
                for (int i = 0; i < names.length; i++) {
                    printWriter.print("(Model: " + names[i] + ", price: " + prices[i] + ")");
                }
            }
            catch (FileNotFoundException | UnsupportedEncodingException e) {
                Logger.getLogger(LinePrint.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            nextTransport.print(transport);
        }
    }

    @Override
    public void setNextTransport(ChainOfResponsibility nextTransport){
        this.nextTransport = nextTransport;
    }}
