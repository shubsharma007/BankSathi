package com.example.bank_ui.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("phoneno")
    @Expose
    private List<String> phoneno;
    @SerializedName("username")
    @Expose
    private List<String> username;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(List<String> phoneno) {
        this.phoneno = phoneno;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

}
