import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Model.Car;
import Model.Car.CarMemento;

import java.io.IOException;

public class TestMemento {

    public static void main(String[] args) throws IOException, DuplicateModelNameException, NoSuchModelNameException, ClassNotFoundException {
        Car car = new Car("Mercedes", 7);
        CarMemento carMemento = car.createMemento();
        System.out.println("Car:");
        System.out.println(car);
        car.setBrand("Not Mercedes");
        car.setNewModelName("model 0", "New Name For Model 0");
        System.out.println("Changed " + car.getBrand());
        System.out.println(car);
        car.setMemento(carMemento);
        System.out.println("Car from memento");
        System.out.println(car);
    }
}
