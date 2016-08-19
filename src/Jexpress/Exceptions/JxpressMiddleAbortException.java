package Jexpress.Exceptions;

/** if exception happens, stopping all middle and process with controller
 * Created by Pengfei on 8/19/2016.
 */
public class JxpressMiddleAbortException extends JxpressException {
    public JxpressMiddleAbortException() {
        super();
    }

    public JxpressMiddleAbortException(String msg) {
        super(msg);
    }
}
