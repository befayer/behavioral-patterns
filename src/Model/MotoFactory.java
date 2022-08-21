package Model;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Interface.Transport;
import Interface.TransportFactory;
import Interface.Vehicle;

public class MotoFactory implements TransportFactory {

    @Override
    public Transport createInstance(String brand, int modelsCount){
        Moto moto = new Moto(brand, modelsCount);
        return moto;
    }
}
