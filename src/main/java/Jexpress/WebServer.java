package Jexpress;

import Jexpress.Connector.JettyWebServer;
import Jexpress.Controller.Controller;
import Jexpress.Filter.IFilter;
import Jexpress.Middleware.Middleware;

import java.util.List;

/**
 * Created by Admin on 8/18/2016.
 */
public interface WebServer {
    /**init Web server
     *
     * @param port listen number
     * @return webserver instance
     */
    WebServer listen(int port);

    /**
     * process get method
     * @param url entire url
     * @param controller called controller
     * @return webserver instance
     */
    WebServer get(String url, Controller controller);

    /**
     * process put method
     * @param url entire url
     * @param controller called controller
     * @return webserver instance
     */
    WebServer put(String url, Controller controller);

    /**
     * process delete method
     * @param url entire url
     * @param controller called controller
     * @return webserver instance
     */
    WebServer delete(String url, Controller controller);

    /**
     * process post method
     * @param url entire url
     * @param controller called controller
     * @return webserver instance
     */
    WebServer post(String url, Controller controller);

    /**
     * Add middleware to server
     * @param middleware middle ware
     * @return webserver instance
     */
    WebServer use(Middleware middleware);

    /**
     * Either post or get method
     * @param url
     * @param controller
     * @return
     */
    WebServer all(String url, Controller controller);

    /**
     * control web server running
     * @return
     */
    WebServer stop();

    /**
     * stop the web server
     * @return
     */
    WebServer start();

    /**
     * get an jetty server instance
     * @return
     */
    public static WebServer jettyServer(){
        return new JettyWebServer();
    }

    WebServer beforeFilter(IFilter filter);


    WebServer afterFilter(IFilter filter);

    public List<String> mountpath();

}
