package Jexpress.Router;

import Jexpress.Controller.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pengfei on 8/19/2016.
 */
public interface Router {
    void addController(String url, Controller controller, String method);

    Controller route(HttpServletRequest request);

    List<String> routeRules();

}
