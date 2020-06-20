package com.ywy.demo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserBean {
    @Property(nameInDb = "id")//通过@Property()这个注解定义我外部数据库的字段名才能解决
    @Id(autoincrement = false)
    private String userId;
    private String name;
    private String headUrl;
    private String phone;
    @Generated(hash = 1015621271)
    public UserBean(String userId, String name, String headUrl, String phone) {
        this.userId = userId;
        this.name = name;
        this.headUrl = headUrl;
        this.phone = phone;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHeadUrl() {
        return this.headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
