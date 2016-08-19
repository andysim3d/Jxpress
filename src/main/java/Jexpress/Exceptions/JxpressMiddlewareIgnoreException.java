package Jexpress.Exceptions;

/** if exception happens, continue process next middleware
 * Created by Pengfei on 8/19/2016.
 */
public class JxpressMiddlewareIgnoreException extends JxpressException{
    public JxpressMiddlewareIgnoreException() {
        super();
    }

    public JxpressMiddlewareIgnoreException(String msg) {
        super(msg);
    }
}
