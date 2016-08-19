package Jexpress.View;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Pengfei on 8/19/2016.
 */
public class StaticView implements View {
    List<String> file;



    @Override
    public void template(String path) {
        try {
            java.nio.file.Path localpath = FileSystems.getDefault().getPath(path);
            this.file = Files.readAllLines(localpath, Charset.forName("utf-8"));
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
    }

    @Override
    public Object render() {

        return file;
    }

    @Override
    public void sendfile(HttpServletResponse response) {
        response.setStatus(200);
        try {
            OutputStreamWriter w = new OutputStreamWriter(response.getOutputStream(), "utf-8");
            for (String i : file) {
                w.write(i);
            }
            w.flush();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
        finally {
            // do nothing
        }
    }
}
