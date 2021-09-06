package com.example.FirstLab.models;

import com.example.FirstLab.models.dto.ClientDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
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
    }


}

