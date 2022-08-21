package Model;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Interface.Transport;
import Visitor.TransportVisitor;

public class SynchronizedTransport implements Transport {

    private Transport transport;

    public SynchronizedTransport(Transport transport){
        this.transport = transport;
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public synchronized String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized String[] getModelNamesArray() {
        return transport.getModelNamesArray();
    }

    @Override
    public synchronized double[] getModelPricesArray() {
        return transport.getModelPricesArray();
    }

    @Override
    public synchronized double getPriceByModelName(String name)  throws NoSuchModelNameException {
        return transport.getPriceByModelName(name);
    }

    @Override
    public synchronized void setPriceByModelName(String name, double newPrice) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setPriceByModelName(name, newPrice);
    }

    @Override
    public synchronized void setNewModelName(String oldName, String newName)  throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setNewModelName(oldName, newName);
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException, NoSuchModelNameException {
        transport.addModel(name, price);
    }

    @Override
    public synchronized void removeModel(String name)  throws NoSuchModelNameException{
        transport.removeModel(name);
    }

    @Override
    public synchronized int getModelArraySize() {
        return transport.getModelArraySize();
    }

    @Override
    public synchronized Transport clone() throws CloneNotSupportedException {
        return transport.clone();
    }

    @Override
    public synchronized void accept(TransportVisitor transportVisitor){
        throw new UnsupportedOperationException("Not support");
    }
}
