package suhael.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns={"/nio", "/nioRecipeServlet"},
        initParams={
                @WebInitParam(name="type", value="checking")
        },
        asyncSupported=true
)
public class nonBlockingRecipeServlet extends HttpServlet {

    String type;

    public void init(ServletConfig config) throws ServletException {
        type = config.getInitParameter("type");
    }

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response) {

        //webservlet 3.1
//        try {
//            AsyncContext context = request.startAsync();
//            ServletInputStream input = null;
//            input = request.getInputStream();
//            input.setReadListener(new ReadListener() {
//                @Override
//                public void onDataAvailable() throws IOException {
//
//                }
//
//                @Override
//                public void onAllDataRead() throws IOException {
//                    try {
//                        ServletHelper.renderView(request, response, "/WEB-INF/views/index.jsp");
//                    } catch (ServletException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onError(Throwable throwable) {
//
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
