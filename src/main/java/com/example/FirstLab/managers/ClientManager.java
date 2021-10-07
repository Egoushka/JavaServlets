package com.example.FirstLab.managers;

import com.example.FirstLab.DAO.ClientDao;
import com.example.FirstLab.DAO.IClientDao;
import com.example.FirstLab.DAO.IDao;
import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.DTO.ClientDto;
import com.example.FirstLab.qualifiers.ClientDaoQualifier;
import com.example.FirstLab.qualifiers.ClientManagerQualifier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
@Default
public class ClientManager implements IClientManager{
    @Inject
    IClientDao dao;

    public IClientDao getDao() {
        return dao;
    }
    public void setDao(IClientDao dao) {
        this.dao = dao;
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
