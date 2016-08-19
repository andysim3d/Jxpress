package Jexpress.Controller;

import java.util.HashMap;

/**
 * Created by Pengfei on 8/18/2016.
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
