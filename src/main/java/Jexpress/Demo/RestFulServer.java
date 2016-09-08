package Jexpress.Demo;

import Jexpress.Controller.ParamMap;
import Jexpress.Controller.ResultMap;
import Jexpress.Controller.JSONController;
import Jexpress.Controller.StaticFileController;
import Jexpress.Filter.IFilter;
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
                public void Process(HttpServletRequest request, HttpServletResponse response){
                    System.out.println("first level" + request.toString());
                }
            })
            .beforeFilter(new IFilter() {
                @Override
                public void preProcess(HttpServletRequest request) {
                    if (request.getParameter("test") == null){
                        System.out.println("not included");
                    }
                    else{
                        System.out.printf("%s is %s\n", "test", request.getParameter("test").toString());
                    }
                }

                @Override
                public void postProcess(HttpServletResponse response) {

                }
            })
            .afterFilter(new IFilter() {
                @Override
                public void preProcess(HttpServletRequest request) {

                }

                @Override
                public void postProcess(HttpServletResponse response) {
                    System.out.println("take 10 seconds");
                }
            })
            .use(new Middleware() {
                @Override
                public void Process(HttpServletRequest request, HttpServletResponse response){
                    System.out.println("first level" + request.toString());

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
