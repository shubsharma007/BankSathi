package com.example.bank_ui.Model.CC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCCLead {

    @SerializedName("lead")
    @Expose
    private List<Lead> lead;

    public List<Lead> getLead() {
        return lead;
    }

    public void setLead(List<Lead> lead) {
        this.lead = lead;
    }

    public class Lead {

        @SerializedName("cardid")
        @Expose
        private String cardid;
        @SerializedName("cardname")
        @Expose
        private String cardname;
        @SerializedName("leadno")
        @Expose
        private String leadno;
        @SerializedName("created_by")
        @Expose
        private String createdBy;

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public String getCardname() {
            return cardname;
        }

        public void setCardname(String cardname) {
            this.cardname = cardname;
        }

        public String getLeadno() {
            return leadno;
        }

        public void setLeadno(String leadno) {
            this.leadno = leadno;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }
    }
}
