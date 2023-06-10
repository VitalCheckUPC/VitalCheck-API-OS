package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    HashMap<String, Object> clientData;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClient(){
        return this.clientRepository.findAll();
    }

    public Optional<Client> getClient(Long clientId){
        return this.clientRepository.findById(clientId);
    }

    public ResponseEntity<Object> newClient(Client client){
        Optional<Client> cl = clientRepository.findByDni(client.getDni());
        clientData = new HashMap<>();

        if (cl.isPresent() && client.getClientId() == null){
            clientData.put("Error", true);
            clientData.put("message", "One client already exist");
            return new ResponseEntity<>(
                    clientData,
                    HttpStatus.CONFLICT
            );
        }
        clientData.put("message", "Save");
        if (client.getClientId() != null){
            clientData.put("message", "Update");
        }
        clientRepository.save(client);
        clientData.put("clientData", client);
        clientData.put("message", "Save");
        return new ResponseEntity<>(
                clientData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteClient(Long clientId){
        clientData = new HashMap<>();
        boolean exists = this.clientRepository.existsById(clientId);
        if (!exists){
            clientData.put("Error", true);
            clientData.put("message", "Client with specified ID does not exist");
            return new ResponseEntity<>(
                    clientData,
                    HttpStatus.CONFLICT
            );
        }
        clientRepository.deleteById(clientId);
        clientData.put("message", "Client Eliminated");
        return new ResponseEntity<>(
                clientData,
                HttpStatus.ACCEPTED
        );
    }
}
