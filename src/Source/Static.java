package Source;
import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Interface.*;
import Model.*;

import java.io.*;
import java.util.Arrays;

public class Static {

    private static TransportFactory factory = new AutoFactory();

    public static void setTransportFactory(TransportFactory factory){
        Static.factory = factory;
    }

    public static Transport createInstance(String brand, int size){
        return factory.createInstance(brand, size);
    }

    public static void printAllNames(Transport transport){
        System.out.println("Массив названий моделей: "+Arrays.toString(transport.getModelNamesArray()));
    }

    public static void printAllPrices(Transport transport){
        System.out.println("Массив цен моделей: "+Arrays.toString(transport.getModelPricesArray()));
    }

    public static double averagePrice(Vehicle vehicle){
        double avg = 0;
        for (int i = 0; i <vehicle.getModelArraySize() ; i++) {
            avg += vehicle.getModelPricesArray()[i];
        }
        return avg;
    }

    public static Transport synchronizedTransport(Transport t){
        return new SynchronizedTransport(t);
    }
}
