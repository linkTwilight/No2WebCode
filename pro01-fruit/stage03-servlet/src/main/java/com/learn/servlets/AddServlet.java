package com.learn.servlets;

import com.learn.dao.FruitDao;
import com.learn.dao.impl.FruitDaoImpl;
import com.learn.entity.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    FruitDao fruitDao = new FruitDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fruit fruit = new Fruit();
        fruit.setFname(req.getParameter("fname"));
        fruit.setPrice(Integer.parseInt(req.getParameter("price")));
        fruit.setFcount(Integer.parseInt(req.getParameter("fcount")));
        fruit.setRemark(req.getParameter("remark"));

        fruitDao.addFruit(fruit);

        System.out.println("添加成功");
    }
}
