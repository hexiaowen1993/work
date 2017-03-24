package com.example.administrator.toutiao.Bean;

/**
 * Created by Administrator on 2017/3/22.
 */

public class GuanzhuBean  {
    private int id;
    private String title;
    private String pic;
    private String url;

    public GuanzhuBean(int id, String title, String pic, String url) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
