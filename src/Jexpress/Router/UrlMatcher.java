package Jexpress.Router;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by Admin on 8/18/2016.
 */
public abstract class UrlMatcher {
    /**
     * match url
     * @param request
     * @return
     */
    public abstract boolean match(HttpServletRequest request);

    public static UrlMatcher compile(String url){
        return RegexMatcher.compile(url);
    }
}
