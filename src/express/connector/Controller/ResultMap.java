package express.connector.Controller;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Admin on 8/18/2016.
 */
public class ResultMap extends HashMap<String, Object> {
    public static ResultMap create(){
        return new ResultMap();
    }

    @Override
    public ResultMap put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
