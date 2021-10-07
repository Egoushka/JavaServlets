package com.example.FirstLab.DAO;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    Optional<T> get(int id);
    List<T> getAll();
    boolean insert(T client) ;
    boolean delete(int id) ;
    boolean clearData();
}
