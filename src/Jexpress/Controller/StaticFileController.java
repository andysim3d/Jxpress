package Jexpress.Controller;

import Jexpress.View.StaticView;
import Jexpress.View.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pengfei on 8/19/2016.
 */
public class StaticFileController extends AbstractController {
    private View staticFile;

    public  StaticFileController(String file){
        staticFile = new StaticView();
        staticFile.template(file);
    }

    @Override
    public void onExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        staticFile.sendfile(response);
    }
}
