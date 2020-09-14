package com.koki.serlvet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class fileDownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String realPath = this.getServletContext().getRealPath("/WEB-INF/classes/蒲公英.jpg");
        System.out.println("realpath:"+realPath);
        String filename = realPath.substring(realPath.lastIndexOf("\\") + 1);
        resp.setHeader("Content-Disposition","attachment;filename"+ URLEncoder.encode(filename,"utf-8"));
        InputStream is=new FileInputStream(realPath);
        ServletOutputStream outputStream = resp.getOutputStream();
        int len=0;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))>0){
            outputStream.write(bytes,0,len);
        }
        outputStream.close();
        is.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
    }
}
