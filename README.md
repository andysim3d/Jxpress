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
            }).listen(8080).start();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
    }
```
