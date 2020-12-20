package com.alle.san.restaurant.models;

public class Person {

    private String name;
    private String email;
    private String phone_number;
    private String profile_image;
    private String user_id;

    public Person(String pName, String pEmail, String phone_number, String profile_image, String user_id) {
        setName(pName);
        setEmail(pEmail);
        setPhone_number(phone_number);
        setProfile_image(profile_image);
        setUser_id(user_id);
    }

    public Person() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
