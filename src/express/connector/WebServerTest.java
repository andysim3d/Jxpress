package express.connector;

import express.connector.Controller.JSONController;
import express.connector.Controller.ParamMap;
import org.testng.annotations.Test;

/**
 * Created by Admin on 8/19/2016.
 */
public class WebServerTest {
    @Test
    public void mountpath() throws Exception {
        WebServer s = WebServer.jettyServer().get("/test", new JSONController() {
            @Override
            public Object JSONify(ParamMap params) {
                return params;
            }
        }).get("/lol", new JSONController() {
            @Override
            public Object JSONify(ParamMap params) {
                return params;
            }
        });
        for (String i : s.mountpath()) {
            System.out.println(i);
        }
    }

}