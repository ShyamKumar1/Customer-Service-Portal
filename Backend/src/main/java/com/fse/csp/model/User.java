package com.fse.csp.model;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "contact_preference")
    private String contactPreference;

    @Column(name = "password")
    private String password;

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    private String user_role;

    public User(){

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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactPreference() {
        return contactPreference;
    }

    public void setContactPreference(String contactPreference) {
        this.contactPreference = contactPreference;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String contactNo, String contactPreference, String password, String user_role){


        this.firstName =firstName;
        this.lastName=lastName;
        this.email=email;
        this.contactNo=contactNo;
        this.contactPreference=contactPreference;
        this.password=password;
        this.user_role=user_role;
    }
}

