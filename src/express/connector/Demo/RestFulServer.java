package express.connector.Demo;

import express.connector.Controller.JSONController;
import express.connector.Controller.ParamMap;
import express.connector.Controller.ResultMap;
import express.connector.WebServer;

/**
 * Created by Pengfei on 8/18/2016.
 */
public class RestFulServer {

    public static void main(String[] args) throws Exception {
        try {
            WebServer.jettyServer().get("/", new JSONController() {
                @Override
                public Object JSONify(ParamMap params) {
                    return ResultMap.create().put("code", 200).put("msg", "ok");
                }
            }).get("/echo", new JSONController() {
                @Override
                public Object JSONify(ParamMap params) {
                    return params;
                }
            }).get("/echo/${id}", new JSONController() {
                @Override
                public Object JSONify(ParamMap params) {
                    return ResultMap.create().put("id", params.getInt("id"));
                }
            }).listen(8080).start();
        }
        catch (Exception exp){
            exp.printStackTrace();
            // do nothing
        }
    }
}
