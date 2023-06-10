package com.example.demo.service;

import com.example.demo.entity.Sale;
import com.example.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    HashMap<String, Object> saleData;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    public List<Sale> getSale(){
        return this.saleRepository.findAll();
    }

    public Optional<Sale> getSale(Long saleId){
        return this.saleRepository.findById(saleId);
    }

    public ResponseEntity<Object> newSale(Sale sale){
        Optional<Sale> id = saleRepository.findBySaleId(sale.getSaleId());
        saleData = new HashMap<>();

        if(id.isPresent() && sale.getSaleId() == null){
            saleData.put("Error", true);
            saleData.put("message", "One sale already exist");
            return new ResponseEntity<>(
                    saleData,
                    HttpStatus.CONFLICT
            );
        }
        saleData.put("message", "Save");
        if (sale.getSaleId() != null){
            saleData.put("message", "Update");
        }
        saleRepository.save(sale);
        saleData.put("saleData", sale);
        saleData.put("message", "Save");
        return new ResponseEntity<>(
                saleData,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteSale(Long saleId){
        saleData = new HashMap<>();
        boolean exists = this.saleRepository.existsById(saleId);
        if (!exists){
            saleData.put("Error", true);
            saleData.put("message", "Sale with specified ID does not exist");
            return new ResponseEntity<>(
                    saleData,
                    HttpStatus.CONFLICT
            );
        }
        saleRepository.deleteById(saleId);
        saleData.put("message", "Sale Eliminated");
        return new ResponseEntity<>(
                saleData,
                HttpStatus.ACCEPTED
        );
    }
}
