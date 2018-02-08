package com.wan.t9_mvp_demo.response;

import java.util.List;

public class Data {

   private List<Integer> collectIds;
   private String icon;
   private int id;
   private String password;
   private int type;
   private String username;


    public void setCollectids(List<Integer> collectids) {
        this.collectIds = collectids;
    }
    public List<Integer> getCollectids() {
        return collectIds;
    }
    

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    
}