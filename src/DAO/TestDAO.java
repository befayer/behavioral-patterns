package DAO;
import Model.*;

public class TestDAO {
    public static void main(String[] args) {
        Car mercedes = new Car("Mercedes", 10);
        System.out.println(mercedes.toString());
        System.out.println("Output test model of TXT DAO");
        DAOFactory<Car> carDAOFactory = new CarDAOFactory();
        DAO<Car> txtDAO = carDAOFactory.getDAO(DAOType.TXT);
        txtDAO.write(mercedes);
        Car txtMercedes = txtDAO.read();
        System.out.println(txtMercedes.toString());
        System.out.println("Output serialized models");
        DAO<Car> serializedDAO = carDAOFactory.getDAO(DAOType.SERIALIZED);
        serializedDAO.write(mercedes);
        Car serializedMercedes = serializedDAO.read();
        System.out.println(serializedMercedes.toString());
    }
}
