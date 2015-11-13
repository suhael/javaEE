package suhael.servlet;

import com.suhael.service.MyAsyncService;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@WebServlet(
        urlPatterns={"/async", "/asyncRecipeServlet"},
        initParams={
                @WebInitParam(name="type", value="checking")
        },
        asyncSupported=true
)
public class AsyncRecipeServlet extends HttpServlet {

    String type;

    public void init(ServletConfig config) throws ServletException {
        type = config.getInitParameter("type");
    }

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response) {
        AsyncContext ac = request.startAsync();
        ac.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                try {
                    ServletHelper.renderView(request, response, "/WEB-INF/views/index.jsp");
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }

            public void onTimeout(AsyncEvent asyncEvent) throws IOException {

            }

            public void onError(AsyncEvent asyncEvent) throws IOException {

            }

            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.execute(new MyAsyncService(ac));
    }
}
