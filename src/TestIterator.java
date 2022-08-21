import Model.Car;

import java.util.Iterator;

public class TestIterator {

    //в Car есть метод toString
    public static void main(String[] args) {
        Car car = new Car("Mercedes", 10);
        Iterator iterator = car.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
