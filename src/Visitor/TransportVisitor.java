package Visitor;

import Model.*;

public interface TransportVisitor {
    void visit(Car car);
    void visit(Moto moto);
}
