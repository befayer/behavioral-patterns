package DAO;

public abstract class DAOFactory<T> {
    abstract public DAO<T> getDAO(DAOType typeDAO);
}
