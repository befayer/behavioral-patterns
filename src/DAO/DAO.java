package DAO;

public abstract class DAO<T> {
    public abstract T read();
    public abstract void write(T obj);
}
