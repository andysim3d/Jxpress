package express.connector.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Admin on 8/18/2016.
 */
public abstract class ContextAwareController extends AbstractController {
    private ThreadLocal<HttpServletRequest> requests = new ThreadLocal<>();

    private ThreadLocal<HttpServletResponse> responses = new ThreadLocal<>();

    protected abstract void doExecute(HttpServletRequest request, HttpServletResponse response) throws  Exception;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //AOP
        requests.set(request);
        responses.set(response);
        beforeExecute(request, response);
        doExecute(request, response);
        afterExecute(request, response);
        requests.remove();
        responses.remove();
    }
    protected  HttpServletRequest getRequest(){
        return requests.get();
    }

    protected HttpServletResponse getResponse(){
        return responses.get();
    }

}
