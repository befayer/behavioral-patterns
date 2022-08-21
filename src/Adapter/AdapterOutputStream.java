package Adapter;

import java.io.IOException;
import java.io.OutputStream;

public class AdapterOutputStream {
    public void adapterOutputStream(String[] array, OutputStream outputStream) throws IOException {
        for(String string : array){
            byte[] bytes = string.getBytes();
            outputStream.write(bytes);
        }
    }
}
