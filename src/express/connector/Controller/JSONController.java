package express.connector.Controller;

import org.eclipse.jetty.util.ajax.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Admin on 8/18/2016.
 */
public abstract class JSONController extends AbstractController {

    @Override
    public void onExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        ParamMap params = new ParamMap();

        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()){
            params.put(stringEntry.getKey(), stringEntry.getValue()[0]);
        }

        Enumeration<String> attributeNames = request.getAttributeNames();

        while (attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            if (request.getAttribute(key) instanceof String){
                params.put(key, request.getAttribute(key).toString());
            }
        }
        Object result = JSONify(params);
        OutputStreamWriter optwriter = new OutputStreamWriter(response.getOutputStream(), "utf-8");
        optwriter.write(JSON.toString(result));
        response.setStatus(200);
        optwriter.flush();
    }


    public abstract Object JSONify(ParamMap params);
}
