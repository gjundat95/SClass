package com.tinhngo.sclassandroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tinhngo on 3/25/17.
 */

public class RegisterModel {

    private String firstName;
    private String lastName;
    private String email;
    private String sex;
    private String phone;
    private String birthday;
    private String description;
    private String address;
    private String company;
    private String relationships;
    private String phoneParent;
    private String password;

    public RegisterModel() {
    }

    public RegisterModel(String firstName, String lastName, String email, String sex, String phone, String birthday, String description, String address, String company, String relationships, String phoneParent, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.birthday = birthday;
        this.description = description;
        this.address = address;
        this.company = company;
        this.relationships = relationships;
        this.phoneParent = phoneParent;
        this.password = password;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRelationships() {
        return relationships;
    }

    public void setRelationships(String relationships) {
        this.relationships = relationships;
    }

    public String getPhoneParent() {
        return phoneParent;
    }

    public void setPhoneParent(String phoneParent) {
        this.phoneParent = phoneParent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
