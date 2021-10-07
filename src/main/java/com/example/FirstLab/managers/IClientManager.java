package com.example.FirstLab.managers;

import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.DTO.ClientDto;

import java.util.List;

public interface IClientManager extends IManager{
    public ClientDto getClient(String email, String password);
    public boolean addClient(String name, String email, String password);
    public boolean delete(int id);
    public List<Client> getAll();
}
