package express.connector.connector;

import express.connector.Controller.Controller;
import express.connector.WebServer;
import express.connector.router.UrlRouter;

import java.util.List;

/**
 * Created by Admin on 8/18/2016.
 */
public abstract class AbstractWebServer implements WebServer {
    private UrlRouter urlRouter = new UrlRouter();

    private int port;

    @Override
    public WebServer get(String url, Controller controller) {
        urlRouter.addController(url,controller,"get");
        return this;
    }

    @Override
    public WebServer post(String url, Controller controller) {
        urlRouter.addController(url,controller,"post");
        return this;
    }

    @Override
    public WebServer listen(int port) {
        this.port = port;
        return this;
    }

    public int getPort(){
        return this.port;
    }

    protected UrlRouter getUrlRouter(){
        return  this.urlRouter;
    }

    @Override
    public List<String> mountpath() {
        return urlRouter.routeRules();
    }
}

