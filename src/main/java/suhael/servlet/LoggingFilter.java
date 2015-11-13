package suhael.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        urlPatterns={"/recipes"},
        initParams={
                @WebInitParam(name="type", value="logging")
        }
)
public class LoggingFilter implements Filter {

    String type;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        type = filterConfig.getInitParameter("type");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
