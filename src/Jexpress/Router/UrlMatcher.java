package Jexpress.Router;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by Pegnfei on 8/18/2016.
 */
public abstract class UrlMatcher {
    /**
     * match url
     * @param request
     * @return true of matched, false as not matched
     */
    public abstract boolean match(HttpServletRequest request);

    public static UrlMatcher compile(String url){
        return RegexMatcher.compile(url);
    }
}
