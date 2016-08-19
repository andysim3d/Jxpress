# Jxpress
A very Simple Express style frame work written in Java

Referring to [code4craft](https://github.com/code4craft)'s express.java .

Running:

```Java
public static void main(String[] args) throws Exception {
        try {
            WebServer.jettyServer().
            //JSON controller, will return JSON file
            .get("/echo/${id}", new JSONController() {
                @Override
                public Object JSONify(ParamMap params) {
                    return ResultMap.create().put("id", params.getInt("id"));
                }
            })
            // staic file View
            .get("/about",StaticFileController.create("C:\\Users\\Admin\\IdeaProjects\\Jxpress\\src\\Jexpress\\template\\test.html"))
            // test post and get, for not matched url
            .all(".*",
                    StaticFileController.create("C:\\Users\\Admin\\IdeaProjects\\Jxpress\\src\\Jexpress\\template\\404.html"))
            .listen(8080).start();
        }
        catch (Exception exp){
            exp.printStackTrace();
            // do nothing
        }
    }
```



more APIs please [goto wiki page](https://github.com/andysim3d/Jxpress/wiki)
