package com.tickets.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_addr", nullable = false, unique = true)
    private String emailAddr;
    private Date birthdate;
    @Column(name = "stripe_id")
    private String stripeId;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailAddr() {
        return this.emailAddr;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public String getStripeId() {
        return this.stripeId;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String fname) {
        this.firstName = fname;
    }

    public void setLastName(String lname) {
        this.lastName = lname;
    }

    public void setEmailAddr(String addr) {
        this.emailAddr = addr;
    }

    public void setBirthdate(Date date) {
        this.birthdate = date;
    }

    public void setStripeId(String id) {
        this.stripeId = id;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
