package Interface;
import Exceptions.*;
import Model.Car;

import java.io.FileOutputStream;

public interface Vehicle extends Cloneable {

    String getBrand();

    void setBrand(String brand);

    void setNewModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    String[] getModelNamesArray();

    double[] getModelPricesArray();

    int getModelArraySize();

    double getPriceByModelName(String name) throws NoSuchModelNameException;

    void setPriceByModelName(String name, double price) throws NoSuchModelNameException;

    void addModel(String name, double price) throws NoSuchModelNameException,DuplicateModelNameException;

    void removeModel(String name) throws NoSuchModelNameException;

    String getModelName();

    double getModelPrice(int i);

}