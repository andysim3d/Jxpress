package Jexpress.View;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pengfei on 8/19/2016.
 */
public interface View {
    void template(String path);

    Object render();

    void sendfile(HttpServletResponse reponse);

}
