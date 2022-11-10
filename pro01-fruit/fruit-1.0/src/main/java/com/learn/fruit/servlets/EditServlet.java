package com.learn.fruit.servlets;

import com.learn.fruit.dao.FruitDao;
import com.learn.fruit.dao.impl.FruitDaoImpl;
import com.learn.fruit.entity.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends ViewBaseServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDao fruitDao = new FruitDaoImpl();
        Integer id = Integer.parseInt(req.getParameter("id"));
        Fruit fruit = fruitDao.getFruitById(id);

        HttpSession session = req.getSession();
        session.setAttribute("fruit",fruit);

        super.processTemplate("edit",req, resp);
    }
}
