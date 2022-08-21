package Interface;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Model.*;
import Visitor.TransportVisitor;

import java.io.Serializable;

public interface Transport extends Cloneable, Serializable {
    void setBrand(String brand);
    String getBrand();
    String[] getModelNamesArray();
    double[] getModelPricesArray();
    double getPriceByModelName(String name) throws NoSuchModelNameException;
    void setPriceByModelName(String name, double newPrice)throws NoSuchModelNameException, DuplicateModelNameException ;
    void setNewModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    void addModel(String name, double price) throws DuplicateModelNameException, NoSuchModelNameException;
    void removeModel(String name)  throws NoSuchModelNameException;
    int getModelArraySize();
    Transport clone() throws CloneNotSupportedException;
    void accept(TransportVisitor transportVisitor);
}
