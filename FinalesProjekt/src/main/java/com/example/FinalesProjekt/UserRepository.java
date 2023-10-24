package com.example.FinalesProjekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findById(long id);
}
