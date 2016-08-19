package Jexpress.Demo;

import Jexpress.Controller.ParamMap;
import Jexpress.Controller.ResultMap;
import Jexpress.Controller.JSONController;
import Jexpress.Controller.StaticFileController;
import Jexpress.WebServer;

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
            }).get("/about",
                    new StaticFileController("C:\\Users\\Admin\\IdeaProjects\\Jxpress\\src\\Jexpress\\template\\test.html"))
            .all(".*", new StaticFileController("C:\\Users\\Admin\\IdeaProjects\\Jxpress\\src\\Jexpress\\template\\404.html"))
            .listen(8080).start();
        }
        catch (Exception exp){
            exp.printStackTrace();
            // do nothing
        }
    }
}
