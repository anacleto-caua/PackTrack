package service;

import interfaces.ClientDTO;

public class ClientService {

    public void save(ClientDTO clientDTO){
        System.out.println("Client save " + clientDTO );
    }
}
