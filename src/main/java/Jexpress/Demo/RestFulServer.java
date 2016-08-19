package Jexpress.Demo;

import Jexpress.Controller.ParamMap;
import Jexpress.Controller.ResultMap;
import Jexpress.Controller.JSONController;
import Jexpress.Controller.StaticFileController;
import Jexpress.Middleware.Middleware;
import Jexpress.WebServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;

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
            }).get("/about",StaticFileController.create("test.html"))
            .use(new Middleware() {
                @Override
                public void PreProcess(HttpServletRequest request) {
                    System.out.println("first level" + request.toString());
                }
                @Override
                public void PostProcess(HttpServletResponse response) {
                    System.out.println("processed, going through");
                }
            })
            .use(new Middleware() {
                @Override
                public void PreProcess(HttpServletRequest request) {
                    System.out.println("second level" + request.toString());
                }

                @Override
                public void PostProcess(HttpServletResponse response) {
                    System.out.println("Continued...");
                }
            })
            .all("/debug/${name}/${gender}",
                    new JSONController() {
                        @Override
                        public Object JSONify(ParamMap params) {
                            return params;
                        }
                    })
            .listen(8080).start();
        }
        catch (Exception exp){
            exp.printStackTrace();
            // do nothing
        }
    }
}
