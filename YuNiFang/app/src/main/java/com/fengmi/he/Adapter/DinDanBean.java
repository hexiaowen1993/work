package com.fengmi.he.Adapter;

/**
 * Created by Administrator on 2017/4/25.
 */

public class DinDanBean  {
    private String name;
    private int pic;
    private int price;

    public DinDanBean(String name, int pic, int price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DinDanBean{" +
                "name='" + name + '\'' +
                ", pic=" + pic +
                ", price=" + price +
                '}';
    }
}
