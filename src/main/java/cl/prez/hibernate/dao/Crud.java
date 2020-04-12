package cl.prez.hibernate.dao;

import java.util.List;

public interface Crud<T, TID> {
    T create(T object);
    
    List<T> findAll();

    T update(T object);

    void deleteById(TID id);

    T findById(TID id);
}