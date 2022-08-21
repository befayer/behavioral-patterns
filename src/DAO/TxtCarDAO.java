package DAO;
import Exceptions.DuplicateModelNameException;
import Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TxtCarDAO extends DAO<Car>{

    private static final String FILE_PATH = "textAuto.txt";

    public TxtCarDAO() {
    }

    @Override
    public Car read() {
        try (FileReader fileReader = new FileReader(FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String brand = bufferedReader.readLine();
            int size = Integer.parseInt(bufferedReader.readLine());
            Car car = new Car(brand, 0);
            String modelName;
            double modelPrice;
            for (int i = 0; i < size; i++) {
                modelName = bufferedReader.readLine();
                modelPrice = Double.parseDouble(bufferedReader.readLine());
                car.addModel(modelName, modelPrice);
            }
            return car;
        } catch (IOException | DuplicateModelNameException ex) {
            Logger.getLogger(TxtCarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void write(Car obj) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)){
            fileWriter.append(obj.getBrand()).append('\n');
            fileWriter.append(String.valueOf(obj.getModelArraySize())).append('\n');
            String[] modelNamesArray = obj.getModelNamesArray();
            double[] modelPricesArray = obj.getModelPricesArray();
            for (int i = 0; i < obj.getModelArraySize(); i++) {
                fileWriter.append(modelNamesArray[i]).append('\n');
                fileWriter.append(String.valueOf(modelPricesArray[i])).append('\n');
            }
        } catch (IOException ex) {
            Logger.getLogger(TxtCarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
