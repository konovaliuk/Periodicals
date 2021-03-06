package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 21.08.2018
 */
public class LoginRegisterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession().getAttribute("user") != null) {
            ((HttpServletResponse) response).sendRedirect("/");
        } else {
            chain.doFilter(request, response);
        }
    }
}
