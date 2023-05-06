package edu.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.senac.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    
}
