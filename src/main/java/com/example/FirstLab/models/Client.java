package com.example.FirstLab.models;

import com.example.FirstLab.models.dto.ClientDto;

import javax.persistence.*;
import java.io.Serializable;
public class Client  {
    int id;
    String name;
    String email;
    String password;
    public Client(int id, String name, String email, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
    }
    public Client(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Client(String email, String password){
        this.email = email;
        this.password = password;
    }
    public Client() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientDto fromEntityToDto(){
        return new ClientDto(getId(), getName(), getEmail());

    }
}

