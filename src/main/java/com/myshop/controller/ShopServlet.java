package com.myshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class ShopServlet extends HttpServlet {
    private static final Logger log = getLogger(ShopServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Hello man!");
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);
        //resp.sendRedirect("index.jsp");
    }
}
