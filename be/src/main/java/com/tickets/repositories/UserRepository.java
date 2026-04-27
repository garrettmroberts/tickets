package com.tickets.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAddr(String emailAddr);
}
