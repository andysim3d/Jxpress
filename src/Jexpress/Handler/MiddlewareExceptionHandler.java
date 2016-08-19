package Jexpress.Handler;

import Jexpress.Controller.StaticFileController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** used to process middleware exceptions
 * Created by Pengfei on 8/19/2016.
 */
public class MiddlewareExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {

    }

    private MiddlewareExceptionHandler(String str){

    }

    public static MiddlewareExceptionHandler create(String str){
        return new MiddlewareExceptionHandler(str);
    }

}
