package Jexpress.Middleware;

import Jexpress.Exceptions.JxpressMiddleAbortException;
import Jexpress.Exceptions.JxpressMiddlewareIgnoreException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** middle interface
 * Created by Pengfei on 8/19/2016.
 */

public interface Middleware {
    //AOP
    // should use decorate pattern

    void Process(HttpServletRequest request,HttpServletResponse response) throws JxpressMiddleAbortException, JxpressMiddlewareIgnoreException;
}
