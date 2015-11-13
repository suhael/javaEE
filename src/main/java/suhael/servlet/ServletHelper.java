package suhael.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletHelper {

    public static void renderView(HttpServletRequest request,
                                  HttpServletResponse response, String jspFileLocation)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(jspFileLocation);
        requestDispatcher.forward(request, response);
    }
}
