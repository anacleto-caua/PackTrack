package dao;

import dao.generic.GenericDAO;
import model.User;

public class UserDAO extends GenericDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    // To add custom queries, no need to overwrite the basic CRUD

}