package Jexpress.Connector;

import Jexpress.Exceptions.JxpressMiddleAbortException;
import Jexpress.Exceptions.JxpressMiddlewareIgnoreException;
import Jexpress.Exceptions.JxpressNotSupportException;
import Jexpress.Exceptions.JxpressUrlNotMatchedException;
import Jexpress.Filter.IFilter;
import Jexpress.Middleware.Middleware;
import Jexpress.WebServer;
import Jexpress.Router.UrlRouter;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** concrete Web server with Jetty
 * Created by Pengfei on 8/18/2016.
 */
public class JettyWebServer extends AbstractWebServer{
    private Server server;

    @Override
    public WebServer stop() {
        if (server != null){
            try {
                server.stop();
            }
            catch (Exception exp){
                // do nothing;
            }
        }
        return this;
    }

    @Override
    public WebServer start() {
        server = new Server(getPort());
        WebServerHandler hdler = new WebServerHandler(getUrlRouter());
        hdler.middlewares = this.middlewareList;
        hdler.postFilter = postFilter;
        hdler.preFilter = preFilter;
        server.setHandler(hdler);

        try{
            server.start();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
        return this;
    }

    private static class WebServerHandler extends AbstractHandler{
        public List<Middleware> middlewares;
        public List<IFilter> preFilter;
        public List<IFilter> postFilter;
        /**
         * process middleware one by one
         * @param httpServletRequest
         * @param httpServletResponse
         */
        private void middlewareProcessIn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

            for (Middleware mid : middlewares){
                try {
                    mid.Process(httpServletRequest, httpServletResponse);
                }
                catch (JxpressMiddlewareIgnoreException e){
                    middlewareContinueHandler.handle(httpServletRequest,httpServletResponse);
                    continue;
                }
                catch (JxpressMiddleAbortException ex){
                    middlewareAbortHandler.handle(httpServletRequest, httpServletResponse);
                    break;
                }
            }
        }

        private  void preFilterProcess(HttpServletRequest request){
            for (IFilter mid : preFilter){
                mid.preProcess(request);
            }
        }

        private void postFilterProcess(HttpServletResponse response){
            for (IFilter mid : postFilter){
                mid.postProcess(response);
            }
        }


        private UrlRouter urlRouter;
        WebServerHandler(UrlRouter urlRouter){
            this.urlRouter = urlRouter;
        }
        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
            try {
                preFilterProcess(httpServletRequest);
                middlewareProcessIn(httpServletRequest,httpServletResponse);
                urlRouter.route(httpServletRequest).execute(httpServletRequest, httpServletResponse);
                postFilterProcess(httpServletResponse);
            }
            catch (JxpressUrlNotMatchedException jex){
                notfindHandler.handle(httpServletRequest, httpServletResponse);
            }
            catch (JxpressNotSupportException jexnotsupport){
                notsupportedHandler.handle(httpServletRequest,httpServletResponse);
            }
            catch (Exception exp){
                exp.printStackTrace();
            }

        }
    }
}
