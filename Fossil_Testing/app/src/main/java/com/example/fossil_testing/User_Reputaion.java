package com.example.fossil_testing;

public class User_Reputaion {

    public String type, change, post_id, creationdt;

    public User_Reputaion(String type, String change, String post_id, String creationdt) {
        this.type = type;
        this.change = change;
        this.post_id = post_id;
        this.creationdt = creationdt;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getCreationdt() {
        return creationdt;
    }

    public void setCreationdt(String creationdt) {
        this.creationdt = creationdt;
    }
}


