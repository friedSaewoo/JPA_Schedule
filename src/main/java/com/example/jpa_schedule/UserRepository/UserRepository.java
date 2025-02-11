package com.example.jpa_schedule.UserRepository;


import com.example.jpa_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
