package express.connector.router;

import express.connector.Controller.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Admin on 8/18/2016.
 */
public class UrlRouter {
    private Map<String, MatchAndController> getRouters = new LinkedHashMap<>();

    private Map<String, MatchAndController> postRouter = new LinkedHashMap<>();

    private List<String> routeRules;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void addController(String url, Controller controller, String method){
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            if ("get".equalsIgnoreCase(method)){
                routeRules.add(url);
                getRouters.put(url, new MatchAndController(UrlMatcher.compile(url), controller));
            }else if ("post".equalsIgnoreCase(method)){
                routeRules.add(url);
                postRouter.put(url, new MatchAndController(UrlMatcher.compile(url), controller));
            }
        }
        finally {
            writeLock.unlock();
        }
    }

    public Controller route(HttpServletRequest request){
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            if ("get".equalsIgnoreCase(request.getMethod())){
                for (MatchAndController matchAndController : getRouters.values()){
                    if (matchAndController.getMatcher().match(request)){
                        return matchAndController.getController();
                    }
                }
            }
            else if ("post".equalsIgnoreCase(request.getMethod())){
                for (MatchAndController matchAndController : postRouter.values()){
                    if (matchAndController.getMatcher().match(request)){
                        return matchAndController.getController();
                    }
                }
            }
        }
        finally {
            readLock.unlock();
        }
        return  null;
    }

    public UrlRouter(){
        this.routeRules = new ArrayList<>();
    }
    public List<String> routeRules(){
        return this.routeRules;
    }

}
