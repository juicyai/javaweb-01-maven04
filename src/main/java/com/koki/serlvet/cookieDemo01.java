package com.koki.serlvet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

public class cookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
//        out.write("上一次登录的时间：");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());

        }
        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName() == "lastLoginTime") {
                    long l = Long.parseLong(cookie.getValue());
                    out.write("<h1>上一次登录的时间：</h1>");
                    out.write(new Date(l).toString());

                }

            }
        } else {
            out.write("<h1>这是第一次登录！</h1>");
            resp.addCookie(new Cookie("lastLoginTime", System.currentTimeMillis() + ""));


        }
    }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doGet(req, resp);
        }
    }

