package com.wan.t9_mvp_demo.response;

public class LoginResponse {

   private Data data;
   private int errorCode;
   private String errorMsg;


    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }
    

    public void setErrorcode(int errorcode) {
        this.errorCode = errorcode;
    }
    public int getErrorcode() {
        return errorCode;
    }
    

    public void setErrormsg(String errormsg) {
        this.errorMsg = errormsg;
    }
    public String getErrormsg() {
        return errorMsg;
    }
    
}


   
