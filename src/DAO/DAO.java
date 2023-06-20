package DAO;

import Models.User;
import Utilities.ConnectionToBDD;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {

    Connection connect = ConnectionToBDD.getInstance();

    // Rechercher par id
    public abstract T find(long id);
    // Tout rechercher
    public abstract ArrayList<T> findAll();
    // Créer une occurrence dans la BDD
    public abstract int create(T obj);

    // Mettre à jour
    public abstract int update(T obj);

    // Supprimer
    public abstract void delete(T obj);
}

