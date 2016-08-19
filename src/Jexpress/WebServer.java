package Jexpress;

import Jexpress.Connector.JettyWebServer;
import Jexpress.Controller.Controller;

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
     * process post method
     * @param url entire url
     * @param controller called controller
     * @return webserver instance
     */
    WebServer post(String url, Controller controller);


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

    public List<String> mountpath();

}
