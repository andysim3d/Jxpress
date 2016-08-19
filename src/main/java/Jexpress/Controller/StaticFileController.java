package Jexpress.Controller;

import Jexpress.View.StaticView;
import Jexpress.View.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created by Pengfei on 8/19/2016.
 */
public class StaticFileController extends AbstractController {
    private View staticFile;

    private StaticFileController (String str){
        staticFile = new StaticView();
        staticFile.template(str);
    }

    /**
     * use this method to create static file controller
     * @param str
     * @return
     */
    public static StaticFileController create(String str){
        return new StaticFileController(str);
    }

    @Override
    public void onExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        staticFile.sendfile(response);
    }
}
