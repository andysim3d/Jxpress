package Jexpress.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pengfei on 8/19/2016.
 */
@FunctionalInterface
public interface ExceptionHandler {
    void handle(HttpServletRequest request, HttpServletResponse response);
}
