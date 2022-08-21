package Interface;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;

public interface TransportFactory {
    Transport createInstance(String mark, int modelsCount);
}