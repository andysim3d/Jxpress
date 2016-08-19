package Jexpress.View;

import javax.servlet.http.HttpServletResponse;

/** Interface of View.
 * Created by Pengfei on 8/19/2016.
 */
public interface View {
    /**
     * set up template location
     * @param path
     */
    void template(String path);

    /**
     * render template and return rendered object
     * @return randered template
     */
    Object render();

    /**
     * send it self to response
     * @param reponse response
     */
    void sendfile(HttpServletResponse reponse);

}
