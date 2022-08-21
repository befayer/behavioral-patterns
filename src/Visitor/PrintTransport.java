package Visitor;

import Interface.Transport;
import Model.Car;
import Model.Moto;

public class PrintTransport implements TransportVisitor {
    @Override
    public void visit(Car car) {
        System.out.println(car.getBrand());
        System.out.print(" ");
        String[] names = car.getModelNamesArray();
        double[] prices = car.getModelPricesArray();
        for (int i = 0; i < car.getModelArraySize(); i++) {
            System.out.print(names[i]);
            System.out.print(" ");
            System.out.print(prices[i]);
            System.out.print(" ");
        }
    }

    @Override
    public void visit(Moto moto) {
        System.out.println(moto.getBrand());
        String[] names = moto.getModelNamesArray();
        double[] prices = moto.getModelPricesArray();
        for (int i = 0; i < moto.getModelArraySize(); i++) {
            System.out.println(names[i]);
            System.out.println(prices[i]);
            System.out.println(" ");
        }
    }
}
