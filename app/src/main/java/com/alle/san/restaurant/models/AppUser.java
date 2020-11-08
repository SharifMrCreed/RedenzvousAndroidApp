package com.alle.san.restaurant.models;

public class AppUser {

    private String user_name;
    private String user_email;
    private String phone_number;
    private String profile_photo;
    private String user_id;



    public AppUser(String pName, String pEmail, String phone_number, String user_id) {
        setUser_name(pName);
        setUser_email(pEmail);
        setPhone_number(phone_number);
        setUser_id(user_id);
    }

    public AppUser() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
