package service;

import dao.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import validator.ValidatorMaster;

import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private ClientDAO clientDAO = new ClientDAO();

    public void save(Client client) {
        clientDAO.save(client);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Client client) {
        clientDAO.delete(client.getId());
    }

    public ObservableList<Client> getClientsList() {
        List<Client> dbList = clientDAO.findAll();
        return FXCollections.observableArrayList(dbList);
    }

    public ArrayList<String> saveOrUpdate(Client client, String clientName, String clientEmail, String clientPhone) {
        ArrayList<String> errors = new ArrayList<>();
        errors.addAll(ValidatorMaster.notEmpty(clientName));
        errors.addAll(ValidatorMaster.isEmail(clientEmail));
        errors.addAll(ValidatorMaster.isPhoneNumber(clientPhone));

        if(!errors.isEmpty()) {
            return errors;
        }

        if (client == null) {
            client = new Client();
        }

        client.setName(clientName);
        client.setEmail(clientEmail);
        client.setPhone(clientPhone);

        if (client.getId() == null) {
            this.save(client);
        } else {
            this.update(client);
        }

        return errors;
    }
}
