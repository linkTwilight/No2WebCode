package com.learn.dao.impl;

import com.learn.base.BaseDao;
import com.learn.dao.FruitDao;
import com.learn.entity.Fruit;

public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {
    String sql;

    @Override
    public void addFruit(Fruit fruit) {
        sql = "INSERT INTO t_fruit (fname, price, fcount, remark) VALUES (?,?,?,?)";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }
}
