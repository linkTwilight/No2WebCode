package com.learn.entity;

public class Fruit {
    private Integer id;
    private String fname;
    private Integer price;
    private Integer fcount;
    private String remark;

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", price=" + price +
                ", fcount=" + fcount +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Fruit() {
    }

    public Fruit(String fname, Integer price, Integer fcount, String remark) {
        this.fname = fname;
        this.price = price;
        this.fcount = fcount;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFcount() {
        return fcount;
    }

    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

