package com.suhael.javaEE.lib;

import com.suhael.SayHello;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestScoped
public class TestServlet extends HttpServlet {

    @Inject
    private SayHello sayHello;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().println(sayHello.getName());
        resp.getOutputStream().println("it works!");
        resp.setStatus(200);
    }
}