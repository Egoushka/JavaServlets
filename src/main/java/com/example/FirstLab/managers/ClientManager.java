package com.example.FirstLab.managers;

import com.example.FirstLab.dao.ClientDao;
import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public class ClientManager{
    ClientDao dao;
    public ClientManager() throws SQLException {
        dao = new ClientDao();
    }
    public List<Client> getAll(){
       return dao.getAll();

    }
    public ClientDto getClient(String email, String password){
        var clients = dao.getAll();
        for (var item : clients ) {
            if(item.getEmail().equals(email)){
                if(item.getPassword().equals(password)){
                    return  ClientDto.fromEntityToDto(item);
                }
                return null;
            }
        }
        return null;
    }
    public boolean addClient(String name, String email, String password){
        var client = new Client(name, email, password);

        dao.insert(client);

        return true;
    }
    public boolean delete(int id){
        return dao.delete(id);
    }
}
