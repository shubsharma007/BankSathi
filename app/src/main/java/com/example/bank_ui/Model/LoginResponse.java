package com.example.bank_ui.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

        @SerializedName("response")
        @Expose
        private String response;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("error_message")
        @Expose
        private String errorMessage;
        @SerializedName("dateofbirth")
        @Expose
        private String dateofbirth;
        @SerializedName("status")
        @Expose
        private String status;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getDateofbirth() {
            return dateofbirth;
        }

        public void setDateofbirth(String dateofbirth) {
            this.dateofbirth = dateofbirth;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
