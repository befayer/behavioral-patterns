import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigManager {

    private static ConfigManager manager;
    private static Properties properties;

    public synchronized static ConfigManager getInstance() throws IOException{
        if (manager == null)
        {
            manager = new ConfigManager();
        }
        return manager;
    }

    public int getVirstProperty()
    {
        return Integer.parseInt(properties.getProperty("FIRST_PROPERTY", "1"));
    }

    public int getSecondProperty()
    {
        return Integer.parseInt(properties.getProperty("SECOND_PROPERTY", "1"));
    }

    public int getThirdProperty()
    {
        return Integer.parseInt(properties.getProperty("THIRD_PROPERTY", "1"));
    }

    private ConfigManager(){
        FileInputStream fis = null;
        try {
            properties = new Properties();
            fis = new FileInputStream("src/config.properties");
            properties.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                assert fis != null;
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
