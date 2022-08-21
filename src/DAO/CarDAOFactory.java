package DAO;
import Model.*;

public class CarDAOFactory extends DAOFactory<Car>{

    @Override
    public DAO<Car> getDAO(DAOType typeDAO){
        if (typeDAO == DAOType.TXT) return new TxtCarDAO();
        if (typeDAO == DAOType.SERIALIZED) return new SerializedCarDAO();
        throw new UnsupportedOperationException("No support type");
    }
}
