package com.example.demo.repository;

import com.example.demo.entity.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {
    Optional<UserPlan> findByPlanName(String planName);
}
