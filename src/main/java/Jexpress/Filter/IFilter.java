package Jexpress.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pengfei on 9/7/2016.
 */
public interface IFilter {
    public void preProcess(HttpServletRequest request);
    public void postProcess(HttpServletResponse response);

}
