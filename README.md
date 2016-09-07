# Jxpress
A very Simple Express style frame work written in Java

Referring to [code4craft](https://github.com/code4craft)'s express.java .

Running:

```Java
public static void main(String[] args) throws Exception {
    try {
        WebServer.jettyServer().get("/", new JSONController() {
            @Override
            public Object JSONify(ParamMap params) {
                return ResultMap.create().put("code", 200).put("msg", "ok");
            }
        }).get("/echo", new JSONController() {
            @Override
            public Object JSONify(ParamMap params) {
                return params;
            }
        }).get("/echo/${id}", new JSONController() {
            @Override
            public Object JSONify(ParamMap params) {
                return ResultMap.create().put("id", params.getInt("id"));
            }
        }).get("/about",StaticFileController.create("template\\test.html"))
        .use(new Middleware() {
            @Override
            public void PreProcess(HttpServletRequest request) {
                System.out.println("first level" + request.toString());
            }
            @Override
            public void PostProcess(HttpServletResponse response) {
                System.out.println("processed, going through");
            }
        })
        .use(new Middleware() {
            @Override
            public void PreProcess(HttpServletRequest request) {
                System.out.println("second level" + request.toString());
            }
    
            @Override
            public void PostProcess(HttpServletResponse response) {
                System.out.println("Continued...");
            }
        })
        .all("/debug/${name}/${gender}",
                new JSONController() {
                    @Override
                    public Object JSONify(ParamMap params) {
                        return params;
                    }
                })
        .listen(8080).start();
    }
    catch (Exception exp){
        exp.printStackTrace();
        // do nothing
    }
}
```



more APIs please [goto wiki page](https://github.com/andysim3d/Jxpress/wiki)
