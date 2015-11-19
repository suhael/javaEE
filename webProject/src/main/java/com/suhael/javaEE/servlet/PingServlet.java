package com.suhael.javaEE.servlet;

import com.suhael.SayHello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PingServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SayHello sayHello = new SayHello();
        resp.getOutputStream().println(sayHello.getName());
        resp.getOutputStream().println("pong");
        resp.setStatus(200);
    }
}