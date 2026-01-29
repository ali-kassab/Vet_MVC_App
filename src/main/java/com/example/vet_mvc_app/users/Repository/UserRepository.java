package com.example.vet_mvc_app.users.Repository;

import com.example.vet_mvc_app.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>  {
    List<User> findAllUsers();
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
