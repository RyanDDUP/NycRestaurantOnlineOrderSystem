package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by RZ on 4/22/16.
 */
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        MyRequest myrequest = new MyRequest(request);
        HttpServletResponse response = (HttpServletResponse)resp;
        chain.doFilter(myrequest,response);
     }


    public void init(FilterConfig config) throws ServletException {

    }

}
