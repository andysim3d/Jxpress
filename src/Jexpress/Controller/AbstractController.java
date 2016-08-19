package Jexpress.Controller;

import Jexpress.Version;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Abstract Controller
 * Created by Admin on 8/19/2016.
 */
public abstract class AbstractController implements Controller {
    public abstract void onExecute(HttpServletRequest request, HttpServletResponse response) throws Exception ;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        beforeExecute(request, response);
        onExecute(request, response);
        afterExecute(request, response);
    }

    @Override
    public void beforeExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader(
                "freme", "Jxpress" + Version.getVersion()
        );
    }

    @Override
    public void afterExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return;
    }
}
