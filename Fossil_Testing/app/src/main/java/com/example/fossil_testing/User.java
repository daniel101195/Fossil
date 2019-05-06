package com.example.fossil_testing;

public class User {
    public String display_name;
    public String location;
    public String reputation;
    public String profile_image;
    public String last_access_date;
    public Integer user_id;

    public User() {
    }

    public User(String display_name, String location, String reputation, String profile_image, String last_access_date, int user_id) {
        this.display_name = display_name;
        this.location = location;
        this.reputation = reputation;
        this.profile_image = profile_image;
        this.last_access_date = last_access_date;
        this.user_id = user_id;
    }


    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getLast_access_date() {
        return last_access_date;
    }

    public void setLast_access_date(String last_access_date) {
        this.last_access_date = last_access_date;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
