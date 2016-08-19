package Jexpress.Router;

import Jexpress.Controller.Controller;
import Jexpress.Exceptions.JxpressException;
import Jexpress.Exceptions.JxpressNotSupportException;
import Jexpress.Exceptions.JxpressUrlNotMatchedException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pengfei on 8/19/2016.
 */
public interface Router {
    void addController(String url, Controller controller, String method) throws JxpressNotSupportException ;

    Controller route(HttpServletRequest request) throws JxpressUrlNotMatchedException, JxpressNotSupportException;

    List<String> routeRules();

}
