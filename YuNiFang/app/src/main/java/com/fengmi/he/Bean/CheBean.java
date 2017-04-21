package com.fengmi.he.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class CheBean {

    /**
     * cartItemList : [{"colorID":0,"count":10,"id":5,"name":"黑泥","pic":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","price":99.9,"productID":1,"repertory":899,"sizeID":0,"userID":123},{"colorID":0,"count":10,"id":6,"name":"美白","pic":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","price":99.9,"productID":1,"repertory":899,"sizeID":0,"userID":123},{"colorID":0,"count":10,"id":7,"name":"面膜\u0097","pic":"http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg","price":99.9,"productID":1,"repertory":899,"sizeID":0,"userID":123}]
     * response : cart
     */

    private String response;
    private List<CartItemListBean> cartItemList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CartItemListBean> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemListBean> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public static class CartItemListBean {
        /**
         * colorID : 0
         * count : 10
         * id : 5
         * name : 黑泥
         * pic : http://image.hmeili.com/yunifang/images/goods/189/goods_img/160819091183119066095185335.jpg
         * price : 99.9
         * productID : 1
         * repertory : 899
         * sizeID : 0
         * userID : 123
         */

        private int colorID;
        private int count;
        private int id;
        private String name;
        private String pic;
        private double price;
        private int productID;
        private int repertory;
        private int sizeID;
        private int userID;

        public int getColorID() {
            return colorID;
        }

        public void setColorID(int colorID) {
            this.colorID = colorID;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public int getRepertory() {
            return repertory;
        }

        public void setRepertory(int repertory) {
            this.repertory = repertory;
        }

        public int getSizeID() {
            return sizeID;
        }

        public void setSizeID(int sizeID) {
            this.sizeID = sizeID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }
    }
}
