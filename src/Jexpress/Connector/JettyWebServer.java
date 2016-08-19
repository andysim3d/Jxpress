package Jexpress.Connector;

import Jexpress.Exceptions.JxpressUrlNotMatchedException;
import Jexpress.WebServer;
import Jexpress.Router.UrlRouter;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        server.setHandler(new WebServerHandler(getUrlRouter()));
        try{
            server.start();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
        return this;
    }

    private static class WebServerHandler extends AbstractHandler{
        private UrlRouter urlRouter;
        WebServerHandler(UrlRouter urlRouter){
            this.urlRouter = urlRouter;
        }
        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
            try {
                urlRouter.route(httpServletRequest).execute(httpServletRequest, httpServletResponse);
            }
            catch (JxpressUrlNotMatchedException jex){
                expHandler.handle(httpServletRequest, httpServletResponse);
            }
            catch (Exception exp){
                exp.printStackTrace();
            }

        }
    }
}
