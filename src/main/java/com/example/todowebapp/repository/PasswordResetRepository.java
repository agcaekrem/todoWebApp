package com.example.todowebapp.repository;

import com.example.todowebapp.model.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset,Long> {
    PasswordReset findByToken (String token);
}
