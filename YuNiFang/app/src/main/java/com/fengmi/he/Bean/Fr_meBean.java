package com.fengmi.he.Bean;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Fr_meBean  {
    private String name;
    private int pic;

    public Fr_meBean(String name, int pic) {
        this.name = name;
        this.pic = pic;
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

    @Override
    public String toString() {
        return "Fr_meBean{" +
                "name='" + name + '\'' +
                ", pic=" + pic +
                '}';
    }
}
