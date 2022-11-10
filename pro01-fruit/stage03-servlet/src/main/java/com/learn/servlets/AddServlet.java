package com.learn.servlets;

import com.learn.dao.FruitDao;
import com.learn.dao.impl.FruitDaoImpl;
import com.learn.entity.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {

    FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Fruit fruit = new Fruit();
        fruit.setFname(req.getParameter("fname"));
        fruit.setPrice(Integer.parseInt(req.getParameter("price")));
        fruit.setFcount(Integer.parseInt(req.getParameter("fcount")));
        fruit.setRemark(req.getParameter("remark"));

        fruitDao.addFruit(fruit);

//        System.out.println("添加成功");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
/*        PrintWriter writer = resp.getWriter();
//        writer.print("<p>添加成功</p>");
        writer.print("<script type = 'text/javascript' > alert('添加成功');window.location.href = 'success.html';        </script >");

        writer.flush();*/
//        转发
//        resp.sendRedirect("success.html");
//        重定向  请求  向内部请求资源  通过forward转发
        req.getRequestDispatcher("success.html").forward(req,resp);


    }
}
