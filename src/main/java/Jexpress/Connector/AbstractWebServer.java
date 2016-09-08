package Jexpress.Connector;

import Jexpress.Controller.Controller;
import Jexpress.Filter.IFilter;
import Jexpress.Handler.ExceptionHandler;
import Jexpress.Handler.MiddlewareExceptionHandler;
import Jexpress.Handler.NotFoundExceptionHandler;
import Jexpress.Middleware.Middleware;
import Jexpress.Router.UrlRouter;
import Jexpress.WebServer;
import com.sun.xml.internal.ws.util.VersionUtil;
import jdk.nashorn.internal.runtime.Version;

import java.util.ArrayList;
import java.util.List;

/** abstract web server
 * Created by Pengfei on 8/18/2016.
 */
public abstract class AbstractWebServer implements WebServer {
    public static ExceptionHandler notfindHandler = NotFoundExceptionHandler.create("404.html");
    public static ExceptionHandler notsupportedHandler = NotFoundExceptionHandler.create("notsupported.html");



    public static ExceptionHandler middlewareContinueHandler = MiddlewareExceptionHandler.create("");
    public static ExceptionHandler middlewareAbortHandler = MiddlewareExceptionHandler.create("");

    protected UrlRouter urlRouter = new UrlRouter();

    protected static List<Middleware> middlewareList = new ArrayList<>();
    protected static List<IFilter> preFilter = new ArrayList<>();
    protected static List<IFilter> postFilter = new ArrayList<>();

    private int port;

    @Override
    public WebServer put(String url, Controller controller) {
        try {
            urlRouter.addController(url,controller,"put");
            return this;
        }
        catch (Exception exp){
            // do nothing
        }finally {
            return  this;
        }
    }

    @Override
    public WebServer use(Middleware middleware) {
        middlewareList.add(middleware);
        return this;
    }

    @Override
    public WebServer delete(String url, Controller controller) {
        try {
            urlRouter.addController(url,controller,"delete");
            return this;
        }
        catch (Exception exp){
            // do nothing
        }finally {
            return  this;
        }
    }

    @Override
    public WebServer get(String url, Controller controller) {
        try {
            urlRouter.addController(url,controller,"get");
            return this;
        }
        catch (Exception exp){
            // do nothing
        }finally {
            return  this;
        }
    }

    @Override
    public WebServer post(String url, Controller controller) {
        try {
            urlRouter.addController(url,controller,"post");
            return this;
        }
        catch (Exception exp){
            // do nothing
        }finally {
            return  this;
        }
    }

    @Override
    public WebServer all(String url, Controller controller){
        try {
            urlRouter.addController(url,controller,"post");
            urlRouter.addController(url,controller,"get");
            urlRouter.addController(url,controller,"put");
            urlRouter.addController(url,controller,"delete");
            return this;
        }
        catch (Exception exp){
            // do nothing
        }finally {
            return  this;
        }
    }

    @Override
    public WebServer beforeFilter(IFilter filter) {
        this.preFilter.add(filter);
        return  this;
    }

    @Override
    public WebServer afterFilter(IFilter filter) {
        this.postFilter.add(filter);
        return  this;
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

