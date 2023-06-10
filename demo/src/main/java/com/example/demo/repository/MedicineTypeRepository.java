package com.example.demo.repository;

import com.example.demo.entity.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineTypeRepository extends JpaRepository<MedicineType,Long> {
    Optional<MedicineType> findByTypeName(String typeName);
}
