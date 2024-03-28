package com.example.papadoner.repository;

import com.example.papadoner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user JOIN user.orders orders WHERE orders IS NOT NULL AND SIZE(orders) > ?1")
    List<User> findUsersWithMoreOrdersThan(int count);

}