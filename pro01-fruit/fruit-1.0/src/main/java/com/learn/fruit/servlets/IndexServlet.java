package com.learn.fruit.servlets;

import com.learn.fruit.dao.FruitDao;
import com.learn.fruit.dao.impl.FruitDaoImpl;
import com.learn.fruit.dao.FruitDao;
import com.learn.fruit.entity.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitList();

        // 使用session
        HttpSession session = req.getSession();
        session.setAttribute("fruitList",fruitList);
//        检测判断是否正常
//        session.setAttribute("fruitList",new ArrayList<Fruit>());

        super.processTemplate("index",req,resp);
    }
}
