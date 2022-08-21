import Interface.Transport;
import Source.Static;
import Visitor.*;
import Model.*;

public class TestVisitor {
    //Посетитель — это поведенческий паттерн проектирования,
    // который позволяет добавлять в программу новые операции,
    // не изменяя классы объектов, над которыми эти операции могут выполняться.

    public static void main(String[] args) {
        System.out.println("Car:");
        Transport auto = Static.createInstance("Mercedes", 5);
        System.out.println(auto.getClass().getName());
        auto.accept(new PrintTransport());
        System.out.println();
        System.out.println("Moto:");
        Static.setTransportFactory(new MotoFactory());
        Transport moto = Static.createInstance("Honda", 7);
        System.out.println(moto.getClass().getName());
        moto.accept(new PrintTransport());
    }
}
