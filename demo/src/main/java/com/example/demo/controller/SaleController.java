package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.Sale;
import com.example.demo.entity.User;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/sale")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getSale(){
        return saleService.getSale();
    }

    @GetMapping("/{saleId}")
    public Optional<Sale> getSale(@PathVariable("saleId") Long saleId){
        return saleService.getSale(saleId);
    }

    @PostMapping
    private ResponseEntity<Object> registerSale(@RequestBody Sale sale){
        //client
        Long clientId = sale.getClient().getClientId();
        Client client = new Client();
        client.setClientId(clientId);
        sale.setClient(client);
        //medicine
        Long medicineid = sale.getMedicine().getMedicineId();
        Medicine medicine = new Medicine();
        medicine.setMedicineId(medicineid);
        sale.setMedicine(medicine);
        //user
        Long userId = sale.getUser().getUserId();
        User user = new User();
        user.setUserId(userId);
        sale.setUser(user);

        return this.saleService.newSale(sale);
    }

    @PutMapping
    private ResponseEntity<Object> updateSale(@RequestBody Sale sale){
        return this.saleService.newSale(sale);
    }

    @DeleteMapping("{saleId}")
    public ResponseEntity<Object> deleteSale(@PathVariable("saleId") Long saleId){
        return this.saleService.deleteSale(saleId);
    }
}
