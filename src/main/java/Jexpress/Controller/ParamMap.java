package Jexpress.Controller;

import java.util.HashMap;

/** parameter map, as hashMap
 * Created by Pengfei on 8/18/2016.
 */
public class ParamMap extends HashMap<String, String>{
    public int getInt(String key){
        String value = get(key);
        if (value == null)
            return 0;
        return Integer.parseInt(value);
    }
}
