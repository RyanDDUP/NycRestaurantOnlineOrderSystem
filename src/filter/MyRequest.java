package filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by RZ on 4/22/16.
 */
public class MyRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }


    @Override
    public String getParameter(String name) {
        if (name==null) return null;
        Map<String,String[]> map = getParameterMap();
        if (map.get(name)==null||map.get(name).length==0) return null;
        return map.get(name)[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        Map<String,String[]> map = getParameterMap();
        if(map.get(name)==null) return  null;
        return map.get(name);
    }

    boolean flag = true;

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = request.getParameterMap();
        if (flag) {
            for (String item : map.keySet()) {
                String[] strs = map.get(item);
                if (strs != null) {
                    for (int i = 0; i < strs.length; i++) {
                        try {
                            strs[i] = new String(strs[i].getBytes("iso8859-1"),"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            flag=false;
        }
        return map;
    }
}
