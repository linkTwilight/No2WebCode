package com.learn.fruit.servlets;

import com.learn.fruit.dao.FruitDao;
import com.learn.fruit.dao.impl.FruitDaoImpl;
import com.learn.fruit.entity.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Fruit fruit = new Fruit();
        fruit.setId(Integer.parseInt(req.getParameter("id")));
        fruit.setFname(req.getParameter("fname"));
        fruit.setPrice(Integer.parseInt(req.getParameter("price")));
        fruit.setFcount(Integer.parseInt(req.getParameter("fcount")));
        fruit.setRemark(req.getParameter("remark"));
        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.updateFruit(fruit);

        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("index");
    }
}
