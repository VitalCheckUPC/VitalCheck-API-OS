package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClient(){
        return clientService.getClient();
    }

    @GetMapping("/{clientId}")
    public Optional<Client> getClient(@PathVariable("clientId") Long clientId){
        return clientService.getClient(clientId);
    }

    @PostMapping
    public ResponseEntity<Object> registerClient(@RequestBody Client client){
        if (client.getDni() !=null && String.valueOf(client.getDni()).length()>8){
            return ResponseEntity.badRequest().body("The DNI must have only 8 digits");
        }
        return this.clientService.newClient(client);
    }

    @PutMapping
    public ResponseEntity<Object> updateClient(@RequestBody Client client){
        return this.clientService.newClient(client);
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<Object> deleteClient(@PathVariable("clientId") Long clientId){
        return this.clientService.deleteClient(clientId);
    }
}
