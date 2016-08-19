package express.connector.Controller;

import java.util.HashMap;

/**
 * Created by Admin on 8/18/2016.
 */
public class ParamMap extends HashMap<String, String>{
    public int getInt(String key){
        String value = get(key);
        if (value == null)
            return 0;
        return Integer.parseInt(value);
    }
}
