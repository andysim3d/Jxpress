package Jexpress.Middleware;

import Jexpress.Exceptions.JxpressMiddleAbortException;
import Jexpress.Exceptions.JxpressMiddlewareIgnoreException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pengfei on 8/19/2016.
 */
public class AbstractMiddleware implements Middleware{


    @Override
    public void Process(HttpServletRequest request, HttpServletResponse response) throws JxpressMiddleAbortException, JxpressMiddlewareIgnoreException {

    }
}
