package Model;

import Interface.*;

public class AutoFactory implements TransportFactory{

    @Override
    public Transport createInstance(String brand, int modelsCount) {
        Car car = new Car (brand, modelsCount);
        return car;
    }
}