package com.sherlock.models;

import java.io.Serializable;

public class UserContact implements Serializable {

    public String username;

    public String userphone;

    private int hashCode;

    public UserContact(String name, String phonenumber) {
        username = name;
        userphone = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

}

