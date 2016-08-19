package Jexpress.Router;

import Jexpress.Controller.Controller;
import Jexpress.Exceptions.JxpressException;
import Jexpress.Exceptions.JxpressNotSupportException;
import Jexpress.Exceptions.JxpressUrlNotMatchedException;
import Jexpress.Handler.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Handler;

/**
 * Created by Pengfei on 8/18/2016.
 */
public class UrlRouter implements Router {
    private Map<String, MatchAndController> getRouters = new LinkedHashMap<>();

    private Map<String, MatchAndController> postRouters = new LinkedHashMap<>();

    private Map<String, MatchAndController> putRouters = new LinkedHashMap<>();

    private Map<String, MatchAndController> deleteRouters = new LinkedHashMap<>();

    private List<String> routeRules;

    private ExceptionHandler handler;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void addController(String url, Controller controller, String method) throws JxpressNotSupportException
    {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            if ("get".equalsIgnoreCase(method)){
                routeRules.add(url);
                getRouters.put(url, new MatchAndController(UrlMatcher.compile(url), controller));
            }else if ("post".equalsIgnoreCase(method)){
                routeRules.add(url);
                postRouters.put(url, new MatchAndController(UrlMatcher.compile(url), controller));
            }else if ("put".equalsIgnoreCase(method)){
                routeRules.add(url);
                putRouters.put(url, new MatchAndController(UrlMatcher.compile(url), controller));
            }else if ("delete".equalsIgnoreCase(method)){
                routeRules.add(url);
                deleteRouters.put(url, new MatchAndController(UrlMatcher.compile(url), controller));
            }
            else {
                throw new JxpressNotSupportException("not implement http method");
            }
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public Controller route(HttpServletRequest request) throws JxpressUrlNotMatchedException, JxpressNotSupportException{
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            if ("get".equalsIgnoreCase(request.getMethod())){
                for (MatchAndController matchAndController : getRouters.values()){
                    if (matchAndController.getMatcher().match(request)){
                        return matchAndController.getController();
                    }
                }
                throw new JxpressUrlNotMatchedException("router not matched");
            }
            else if ("post".equalsIgnoreCase(request.getMethod())){
                for (MatchAndController matchAndController : postRouters.values()){
                    if (matchAndController.getMatcher().match(request)){
                        return matchAndController.getController();
                    }
                }
                throw new JxpressUrlNotMatchedException("router not matched");
            }else if ("put".equalsIgnoreCase(request.getMethod())){
                for (MatchAndController matchAndController : putRouters.values()){
                    if (matchAndController.getMatcher().match(request)){
                        return matchAndController.getController();
                    }
                }
                throw new JxpressUrlNotMatchedException("router not matched");
            }
            else if ("delete".equalsIgnoreCase(request.getMethod())){
                for (MatchAndController matchAndController : deleteRouters.values()){
                    if (matchAndController.getMatcher().match(request)){
                        return matchAndController.getController();
                    }
                }
                throw new JxpressUrlNotMatchedException("router not matched");
            }
            throw new JxpressNotSupportException("not supported Http method");
        }
        finally {
            readLock.unlock();
        }
    }

    public UrlRouter(){
        this.routeRules = new ArrayList<>();
    }
    @Override
    public List<String> routeRules(){
        return this.routeRules;
    }

}
