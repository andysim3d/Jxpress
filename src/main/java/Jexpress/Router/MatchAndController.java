package Jexpress.Router;

import Jexpress.Controller.Controller;

/** combinator of matcher and Controller
 * Created by Pengfei on 8/18/2016.
 */
public class MatchAndController {
    private final UrlMatcher matcher;
    private final Controller controller;

    public MatchAndController(UrlMatcher matcher, Controller controller){
        this.controller = controller;
        this.matcher = matcher;
    }

    public UrlMatcher getMatcher(){
        return matcher;
    }

    public Controller getController(){
        return controller;
    }

}
