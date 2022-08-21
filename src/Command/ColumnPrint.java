package Command;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import Model.Car;

public class ColumnPrint implements Command {

    @Override
    public void execute(Car car, OutputStream outputStream) {
        try {
            outputStream.write((car.getBrand() + ":").getBytes());
            String[] names = car.getModelNamesArray();
            double[] prices = car.getModelPricesArray();
            for (int i = 0; i < names.length; i++) {
                outputStream.write(("\n(Model: " + names[i] + ", price: " + prices[i] + ")").getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
