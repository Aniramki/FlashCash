package com.Aniramki.FlashCash.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class User {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private  String lastName;
    @Column(unique = true)
    private  String email;
    private String password;
    @ManyToMany
    private List<Link> links;
    private String color;



    public User(String firstName, String lastName, String email, String password, UserAccount account, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.account = account;
        this.color = color;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserAccount account;





    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
