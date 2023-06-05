package com.example.bank_ui.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddCustomerResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("contactno")
    @Expose
    private List<String> contactno;
    @SerializedName("pancardno")
    @Expose
    private List<String> pancardno;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getContactno() {
        return contactno;
    }

    public void setContactno(List<String> contactno) {
        this.contactno = contactno;
    }

    public List<String> getPancardno() {
        return pancardno;
    }

    public void setPancardno(List<String> pancardno) {
        this.pancardno = pancardno;
    }

}
