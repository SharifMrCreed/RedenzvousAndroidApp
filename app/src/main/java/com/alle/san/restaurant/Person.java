package com.alle.san.restaurant;

public class Person {

    private String PName;
    private String PEmail;
    private String Ppassword;

    public Person(String pName, String pEmail, String ppassword) {
        setPName(pName);
        setPEmail(pEmail);
        setPpassword(ppassword);
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPEmail() {
        return PEmail;
    }

    public void setPEmail(String PEmail) {
        this.PEmail = PEmail;
    }

    public String getPpassword() {
        return Ppassword;
    }

    public void setPpassword(String ppassword) {
        Ppassword = ppassword;
    }
}
