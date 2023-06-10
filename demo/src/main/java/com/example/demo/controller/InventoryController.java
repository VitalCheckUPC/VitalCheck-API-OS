package com.example.demo.controller;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.User;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getInventory(){
        return inventoryService.getInventory();
    }

    @GetMapping("/{inventoryId}")
    public Optional<Inventory> getInventory(@PathVariable("inventoryId") Long inventoryId){
        return inventoryService.getInventory(inventoryId);
    }

    @PostMapping
    public ResponseEntity<Object> registerInventory(@RequestBody Inventory inventory){
        Long medicineId = inventory.getMedicine().getMedicineId();
        Medicine medicine = new Medicine();
        medicine.setMedicineId(medicineId);
        inventory.setMedicine(medicine);

        Long userId = inventory.getUser().getUserId();
        User user = new User();
        user.setUserId(userId);
        inventory.setUser(user);

        return this.inventoryService.newInventory(inventory);
    }

    @PutMapping
    public ResponseEntity<Object> updateInventory(@RequestBody Inventory inventory){
        return this.inventoryService.newInventory(inventory);
    }

    @DeleteMapping("{inventoryId}")
    public ResponseEntity<Object> deleteInventory(@PathVariable("inventoryId") Long inventoryId){
        return this.inventoryService.deleteInventory(inventoryId);
    }

}
