package com.example.bank_ui.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditCardResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cardname")
    @Expose
    private String cardname;

    @SerializedName("dematname")
    @Expose
    private String dematname;

    @SerializedName("bankname")
    @Expose
    private String bankname;

    @SerializedName("psname")
    @Expose
    private String psname;

    @SerializedName("invname")
    @Expose
    private String invname;

    @SerializedName("clname")
    @Expose
    private String clname;

    @SerializedName("discription")
    @Expose
    private String discription;
    @SerializedName("earnupto")
    @Expose
    private String earnupto;
    @SerializedName("total_earn")
    @Expose
    private String totalEarn;
    @SerializedName("total_lead")
    @Expose
    private String totalLead;
    @SerializedName("toatl_sale")
    @Expose
    private String toatlSale;
    @SerializedName("offer1")
    @Expose
    private String offer1;
    @SerializedName("offer2")
    @Expose
    private String offer2;
    @SerializedName("offer3")
    @Expose
    private String offer3;
    @SerializedName("details")
    @Expose
    private Object details;
    @SerializedName("training")
    @Expose
    private Object training;
    @SerializedName("marketing")
    @Expose
    private Object marketing;
    @SerializedName("banklogo")
    @Expose
    private String banklogo;
    @SerializedName("joinfees")
    @Expose
    private String joinfees;
    @SerializedName("annualfees")
    @Expose
    private String annualfees;
    @SerializedName("active_status")
    @Expose
    private Boolean activeStatus;
    @SerializedName("user")
    @Expose
    private User user;


    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    public String getPsname() {
        return psname;
    }

    public void setPsname(String psname) {
        this.psname = psname;
    }

    public String getDematname() {
        return dematname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public void setDematname(String dematname) {
        this.dematname = dematname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getEarnupto() {
        return earnupto;
    }

    public void setEarnupto(String earnupto) {
        this.earnupto = earnupto;
    }

    public String getTotalEarn() {
        return totalEarn;
    }

    public void setTotalEarn(String totalEarn) {
        this.totalEarn = totalEarn;
    }

    public String getTotalLead() {
        return totalLead;
    }

    public void setTotalLead(String totalLead) {
        this.totalLead = totalLead;
    }

    public String getToatlSale() {
        return toatlSale;
    }

    public void setToatlSale(String toatlSale) {
        this.toatlSale = toatlSale;
    }

    public String getOffer1() {
        return offer1;
    }

    public void setOffer1(String offer1) {
        this.offer1 = offer1;
    }

    public String getOffer2() {
        return offer2;
    }

    public void setOffer2(String offer2) {
        this.offer2 = offer2;
    }

    public String getOffer3() {
        return offer3;
    }

    public void setOffer3(String offer3) {
        this.offer3 = offer3;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getTraining() {
        return training;
    }

    public void setTraining(Object training) {
        this.training = training;
    }

    public Object getMarketing() {
        return marketing;
    }

    public void setMarketing(Object marketing) {
        this.marketing = marketing;
    }

    public String getBanklogo() {
        return banklogo;
    }

    public void setBanklogo(String banklogo) {
        this.banklogo = banklogo;
    }

    public String getJoinfees() {
        return joinfees;
    }

    public void setJoinfees(String joinfees) {
        this.joinfees = joinfees;
    }

    public String getAnnualfees() {
        return annualfees;
    }

    public void setAnnualfees(String annualfees) {
        this.annualfees = annualfees;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("last_login")
        @Expose
        private String lastLogin;
        @SerializedName("is_superuser")
        @Expose
        private Boolean isSuperuser;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("is_staff")
        @Expose
        private Boolean isStaff;
        @SerializedName("is_active")
        @Expose
        private Boolean isActive;
        @SerializedName("date_joined")
        @Expose
        private String dateJoined;
        @SerializedName("phoneno")
        @Expose
        private String phoneno;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("confirm_password")
        @Expose
        private String confirmPassword;
        @SerializedName("dateofbirth")
        @Expose
        private Object dateofbirth;
        @SerializedName("address")
        @Expose
        private Object address;
        @SerializedName("pincode")
        @Expose
        private Object pincode;
        @SerializedName("profileimg")
        @Expose
        private Object profileimg;
        @SerializedName("groups")
        @Expose
        private List<Object> groups;
        @SerializedName("user_permissions")
        @Expose
        private List<Object> userPermissions;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public Boolean getIsSuperuser() {
            return isSuperuser;
        }

        public void setIsSuperuser(Boolean isSuperuser) {
            this.isSuperuser = isSuperuser;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Boolean getIsStaff() {
            return isStaff;
        }

        public void setIsStaff(Boolean isStaff) {
            this.isStaff = isStaff;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getDateJoined() {
            return dateJoined;
        }

        public void setDateJoined(String dateJoined) {
            this.dateJoined = dateJoined;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public Object getDateofbirth() {
            return dateofbirth;
        }

        public void setDateofbirth(Object dateofbirth) {
            this.dateofbirth = dateofbirth;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getPincode() {
            return pincode;
        }

        public void setPincode(Object pincode) {
            this.pincode = pincode;
        }

        public Object getProfileimg() {
            return profileimg;
        }

        public void setProfileimg(Object profileimg) {
            this.profileimg = profileimg;
        }

        public List<Object> getGroups() {
            return groups;
        }

        public void setGroups(List<Object> groups) {
            this.groups = groups;
        }

        public List<Object> getUserPermissions() {
            return userPermissions;
        }

        public void setUserPermissions(List<Object> userPermissions) {
            this.userPermissions = userPermissions;
        }


        }
    }
