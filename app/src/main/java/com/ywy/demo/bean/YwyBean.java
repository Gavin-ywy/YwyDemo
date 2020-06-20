package com.ywy.demo.bean;

public class YwyBean {

    private String name;
    private String age;
    private String height;
    private String weight;

    public YwyBean() {
    }

    public YwyBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public YwyBean(String name, String height, String weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "YwyBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
