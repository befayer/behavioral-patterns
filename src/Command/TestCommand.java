package Command;

import Model.Car;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestCommand {
    //превращает запросы в объекты, позволяя передавать их как аргументы

    public static void main(String[] args) throws IOException {
        Car car = new Car("Mercedes", 10);

        try (FileOutputStream fileOutputStream = new FileOutputStream("Transport " + car.getBrand() + " in line.txt")) {
            car.setPrintCommand(new LinePrint());
            car.printCar(fileOutputStream);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("Transport " + car.getBrand() + " in column.txt")) {
            car.setPrintCommand(new ColumnPrint());
            car.printCar(fileOutputStream);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
