package Jexpress.Handler;

import Jexpress.Controller.StaticFileController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pengfei on 8/19/2016.
 */
public class NotFoundExceptionHandler implements ExceptionHandler {

    private StaticFileController pageNotFound;

    private NotFoundExceptionHandler(String str){
        pageNotFound = StaticFileController.create(str);
    }

    public static NotFoundExceptionHandler create(String str){
        return new NotFoundExceptionHandler(str);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response){
        try {
            pageNotFound.execute(request, response);
        }
        // if failed again
        catch (Exception exp){
            response.reset();
            response.setStatus(404);
        }
    }
}
