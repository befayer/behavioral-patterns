package DAO;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.*;

public class SerializedCarDAO extends DAO<Car>{

    private static final String FILE_PATH = "serializedCar";

    public SerializedCarDAO(){}

    @Override
    public Car read() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Car car = (Car) in.readObject();
            return car;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SerializedCarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void write(Car obj) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(obj);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(SerializedCarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
