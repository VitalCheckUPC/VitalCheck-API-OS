package com.example.demo.service;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    HashMap<String, Object> inventoryData;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventory(){
        return this.inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventory(Long inventoryId){
        return this.inventoryRepository.findById(inventoryId);
    }

    public ResponseEntity<Object> newInventory(Inventory inventory){
        Optional<Inventory> id = inventoryRepository.findByInventoryId(inventory.getInventoryId());

        inventoryData = new HashMap<>();
        if (id.isPresent() && inventory.getInventoryId() == null){
            inventoryData.put("Error", true);
            inventoryData.put("message", "One inventory already exist");
            return new ResponseEntity<>(
                    inventoryData,
                    HttpStatus.CONFLICT
            );
        }
        inventoryData.put("message", "Save");
        if (inventory.getInventoryId() != null){
            inventoryData.put("message", "Update");
        }
        inventoryRepository.save(inventory);
        inventoryData.put("inventoryData", inventory);
        inventoryData.put("message", "Save");
        return new ResponseEntity<>(
                inventoryData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteInventory(Long inventoryId){
        inventoryData = new HashMap<>();
        boolean exists = this.inventoryRepository.existsById(inventoryId);
        if (!exists){
            inventoryData.put("Error", true);
            inventoryData.put("message", "Inventory with specified ID does not exist");
            return new ResponseEntity<>(
                    inventoryData,
                    HttpStatus.CONFLICT
            );
        }
        inventoryRepository.deleteById(inventoryId);
        inventoryData.put("message", "Inventory Eliminated");
        return new ResponseEntity<>(
                inventoryData,
                HttpStatus.ACCEPTED
        );
    }
}
