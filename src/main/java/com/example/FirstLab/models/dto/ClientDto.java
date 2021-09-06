package com.example.FirstLab.models.dto;

import com.example.FirstLab.models.Client;
import lombok.Data;

@Data
public class ClientDto {
    int id;
    String name;
    String email;

    public ClientDto(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public static ClientDto fromEntityToDto(Client client){
        return new ClientDto(client.getId(), client.getName(), client.getEmail());

    }
}
