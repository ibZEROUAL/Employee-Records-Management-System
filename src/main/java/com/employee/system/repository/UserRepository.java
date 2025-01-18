package com.employee.system.repository;

import com.employee.system.enums.Role;
import com.employee.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findAllByRole(@Param("role") Role role);
}