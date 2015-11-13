package suhael.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns={"/recipes", "/recipeServlet"},
        initParams={
                @WebInitParam(name="type", value="checking")
        }
)
public class RecipeServlet extends HttpServlet {

    String type;

    public void init(ServletConfig config) throws ServletException {
        type = config.getInitParameter("type");
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {
        String txValue = request.getParameter("tx");

        //servlet context
        ServletContext context = request.getServletContext();

        //cookie config
        SessionCookieConfig config = context.getSessionCookieConfig();
        //config.setHttpOnly(true);

        //session
        HttpSession session = request.getSession(true);
        session.setAttribute("name", new String("suhael"));

        //request.getRequestDispatcher("bank").forward(request, response);

        try {
            ServletHelper.renderView(request, response, "/WEB-INF/views/index.jsp");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
