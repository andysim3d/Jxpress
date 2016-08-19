package Jexpress.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * abstract Controller, used for define Interface
 * Created by Admin on 8/18/2016.
 */
public interface Controller {
    // main logic
    void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     *
     // before execute, pre process
     * @param request request
     * @param response response
     * @throws Exception any exception when process
     */
    void beforeExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * after execute, logging or
     * @param request request
     * @param response response
     * @throws Exception any exception
     */
    void afterExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
