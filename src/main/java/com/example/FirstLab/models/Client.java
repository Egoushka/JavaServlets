package com.example.FirstLab.models;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.validation.constraints.NotNull;

@Data
@Stateless
public class Client  {
    @NotNull
    int id;
    @NotNull
    String name;
    @NotNull
    String email;
    @NotNull
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
        this.email = "";
    }
    @AroundConstruct
    public void beforeConstruct(){
        System.out.println("Client are creating");
    }
    @PostConstruct
    public void afterConstruct(){
        System.out.println("Client created");
    }
    @PreDestroy
    public void beforeDestroy(){
        System.out.println("Client are destroing");
    }


}

