package dao;

import dao.generic.GenericDAO;
import model.Client;

public class ClientDAO extends GenericDAO<Client> {
    public ClientDAO() {
        super(Client.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}
