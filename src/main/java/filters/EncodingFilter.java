package filters;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Created by Julia on 20.08.2018
 */
public class EncodingFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        super.doFilter(request, response, chain);
    }
}
