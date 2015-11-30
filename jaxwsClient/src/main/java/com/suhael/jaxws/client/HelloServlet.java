package com.suhael.jaxws.client;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        urlPatterns={"/recipes"},
        initParams={
                @WebInitParam(name="type", value="checking")
        }
)
public class HelloServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {
        System.out.println("heeloo");
    }
}
