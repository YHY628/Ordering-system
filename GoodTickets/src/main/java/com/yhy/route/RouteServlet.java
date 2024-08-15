package com.yhy.route;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RouteServlet extends HttpServlet {
    private final static String PREFIX = "WEB-INF/views";
    private final static String SUFFIX = ".html";
    private final static String REQUEST_PREFIX = "v";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("*************");
        String url = req.getRequestURI();
        req.getRequestDispatcher(viewName(url)).forward(req, resp);
    }

    private String viewName(String url) {
        return url.replace(REQUEST_PREFIX, PREFIX) + SUFFIX;
    }

}


