package express.connector.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * abstract Controller, used for define Interface
 * Created by Admin on 8/18/2016.
 */
public interface Controller {
    // main logic
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     *
     // before execute, pre process
     * @param request
     * @param response
     * @throws Exception
     */
    public void beforeExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * after execute, logging or
     * @param request request
     * @param response response
     * @throws Exception any exception
     */
    public void afterExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
