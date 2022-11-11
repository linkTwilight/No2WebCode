package com.learn.fruit.servlets;

import com.learn.fruit.dao.FruitDao;
import com.learn.fruit.dao.impl.FruitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del")
public class DeleteServlet extends ViewBaseServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.delFruit(Integer.parseInt(req.getParameter("id")));

        resp.sendRedirect("index");
    }
}
