package com.product.api.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, UUID>{
    Optional<UserModel> findByEmail(String email);
}
