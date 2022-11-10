package com.learn.fruit.dao.impl;

import com.learn.fruit.base.BaseDao;
import com.learn.fruit.dao.FruitDao;
import com.learn.fruit.entity.Fruit;

import java.util.List;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    String sql;

    @Override
    public void addFruit(Fruit fruit) {
        sql = "INSERT INTO t_fruit (fname, price, fcount, remark) VALUES (?,?,?,?)";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    @Override
    public List<Fruit> getFruitList() {
        sql = "SELECT * FROM t_fruit";
        return super.executeQuery(sql);
    }

    @Override
    public Fruit getFruitById(Integer id) {
        sql="SELECT * FROM t_fruit WHERE id=?";
        return super.load(sql,id);
    }
}
