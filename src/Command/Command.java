package Command;

import java.io.OutputStream;
import Model.Car;

public interface Command {
    void execute(Car car, OutputStream outputStream);
}
