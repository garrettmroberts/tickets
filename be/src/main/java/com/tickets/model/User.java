package com.tickets.model;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private String email_addr;
    private Date birthdate;
    private String stripe_id;

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getEmailAddr() {
        return this.email_addr;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public String getStripeId() {
        return this.stripe_id;
    }

    public void setFirstName(String fname) {
        this.first_name = fname;
    }

    public void setLastName(String lname) {
        this.last_name = lname;
    }

    public void setEmailAddr(String addr) {
        this.email_addr = addr;
    }

    public void setBirthdate(Date date) {
        this.birthdate = date;
    }

    public void setStripeId(String id) {
        this.stripe_id = id;
    }
}
