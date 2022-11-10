package com.learn.fruit.dao;

import com.learn.entity.Fruit;
import com.learn.fruit.entity.Fruit;

import java.util.List;

public interface FruitDao {
    void addFruit(Fruit fruit);

    List<Fruit> getFruitList();
}