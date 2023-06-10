package com.example.demo.repository;

import com.example.demo.entity.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispatchRepository  extends JpaRepository<Dispatch, Long> {
    Optional<Dispatch> findByDispatchId(Long dispatchId);
}
