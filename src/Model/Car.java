package Model;

import Command.Command;
import Exceptions.DuplicateModelNameException;
import Exceptions.ModelPriceOutOfBoundsException;
import Exceptions.NoSuchModelNameException;
import Interface.Transport;
import Interface.Vehicle;
import Visitor.*;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Car implements Transport, Serializable {

    private String brand;
    private Command printCommand;

    public Car(String brand, int n){
        this.brand = brand;
        modelArray = new Model[n];
        for (int i = 0; i < n; i++){
            modelArray[i] = new Model("model " + i, 0);
        }
    }

    public String getBrand(){
        return brand;
    }

    @Override
    public void setBrand(String brand){
        this.brand = brand;
    }

    public String[] getModelNamesArray(){
        String[] modelNamesArray = new String[modelArray.length];
        for (int i = 0; i < modelNamesArray.length; i++){
            if (modelArray[i] != null){
                modelNamesArray[i] = modelArray[i].getModelName();
            }
        }
        return modelNamesArray;
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        Car car = (Car)super.clone();
        car.modelArray = new Model[getModelArraySize()];
        for(int i = 0; i<getModelArraySize();i++)
            car.modelArray[i] = (Model) modelArray[i].clone();
        return car;
    }

    @Override
    public void setNewModelName(String oldNAme, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int id = -1;
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(oldNAme)) {
                id = i;

            }
            if(modelArray[i].modelName.equals(newName)) throw new DuplicateModelNameException("Модель с названием " + "'" + newName + "'" + " уже существует");
        }

        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else modelArray[id].modelName = newName;
    }

    @Override
    public double[] getModelPricesArray(){
        double[] modelPricesArray = new double[modelArray.length];
        for (int i = 0; i < modelPricesArray.length; i++){
            if (modelArray[i] != null){
                modelPricesArray[i] = modelArray[i].getModelPrice();
            }
        }
        return modelPricesArray;
    }

    @Override
    public double getPriceByModelName(String modelName) throws NoSuchModelNameException{
        int id = -1;
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)){
                id = i;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Модели с данным названием не существует");
        else  return modelArray[id].getModelPrice();
    }

    @Override
    public void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException{
        int id = -1;
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException("Введено некорректное значение цены");
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)){
                id = 1;
                modelArray[i].setModelPrice(newPrice);
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Модели с данным названием не существует");
    }

    /*@Override
    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException{
        if (modelPrice < 0) throw new ModelPriceOutOfBoundsException("Введено некорректное значение цены");
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)) throw new DuplicateModelNameException("Модель с названием " + modelName + " уже существует");
        }
        modelArray = Arrays.copyOf(modelArray, modelArray.length + 1);
        modelArray[modelName.length() - 1] = new Model(modelName, modelPrice);
    }*/

    @Override
    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException {
        if (modelPrice < 0) throw new ModelPriceOutOfBoundsException("Введено некорректное значение цены");
        int i = 0;
        int j = -1;
        while (i < modelArray.length) {
            if (modelArray[i].modelName.equals(modelName)) {
                j = i;
                break;
            }
            i++;
        }
        if (j >= 0) {
            throw new DuplicateModelNameException("Модель с названием " + modelName + " уже существует");
        }
        modelArray = Arrays.copyOf(modelArray, modelArray.length + 1);
        modelArray[modelArray.length - 1] = new Model(modelName, modelPrice);
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException{
        int id = -1;
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)){
                id = i;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Модели с данным названием не существует");
        System.arraycopy(modelArray, id+1, modelArray, id, modelArray.length - id - 1);
        modelArray = Arrays.copyOf(modelArray, modelArray.length - 1);
    }

    public void setPrintCommand(Command command){
        printCommand = command;
    }

    public void printCar(OutputStream outputStream){
        printCommand.execute(this, outputStream);
    }

    @Override
    public int getModelArraySize(){
        return modelArray.length;
    }

    private Model[] modelArray;

    protected static class Model implements Cloneable, Serializable {
        private double modelPrice = Double.NaN;
        private String modelName = null;

        public Model(String modelName, double modelPrice){
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            Model model = (Model) super.clone();
            return model;
        }

        public void setModelName(String modelName){
            this.modelName = modelName;
        }

        public void setModelPrice(double modelPrice){
            this.modelPrice = modelPrice;
        }

        public String getModelName(){
            return modelName;
        }

        public double getModelPrice(){
            return modelPrice;
        }

        @Override
        public String toString(){
            return "Model name: " + modelName + ", price: " + modelPrice;
        }

    }

    class CarIterator implements Iterator {
        private int index = 0;
        private final Car car;

        public CarIterator(Car carLink){
            car = carLink;
        }

        @Override
        public boolean hasNext() {
            return index + 1 < car.modelArray.length;
        }

        @Override
        public Model next() {
            return modelArray[index++];
        }
    }

    public CarIterator iterator() {
        return new CarIterator(this);
    }

    public static class CarMemento {

        private byte[] state;

        public void setCar(Car carLink) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            //dataOutputStream.writeUTF(carLink.brand);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(carLink);

            /*dataOutputStream.writeInt(carLink.modelArray.length);
            for (Model model: carLink.modelArray){
                dataOutputStream.writeUTF(model.modelName);
                dataOutputStream.writeDouble(model.modelPrice);
            }
            dataOutputStream.flush();*/
            state = byteArrayOutputStream.toByteArray();
        }

        public void getCar(Car carLink) throws IOException, ClassNotFoundException {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(state);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            carLink = (Car) objectInputStream.readObject();


            /*int size = dataInputStream.readInt();
            carLink.modelArray = new Model[size];
            for (int i = 0; i < carLink.modelArray.length; i++) {
                carLink.modelArray[i] = new Model(dataInputStream.readUTF(), dataInputStream.readDouble());
            }*/
            //}
        }
    }

    public CarMemento createMemento() throws IOException{
        CarMemento carMemento = new CarMemento();
        carMemento.setCar(this);
        return carMemento;
    }

    public void setMemento(CarMemento carMemento) throws IOException, ClassNotFoundException {
        carMemento.getCar(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(brand).append("\n");
        stringBuilder.append("Models: \n");
        for (Model model: modelArray){
            stringBuilder.append("   ").append(model.modelName).append("  ").append(model.modelPrice).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void accept(TransportVisitor visitor) {
        visitor.visit(this);
    }
}
