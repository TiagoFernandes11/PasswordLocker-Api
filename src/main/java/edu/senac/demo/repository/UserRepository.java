package edu.senac.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.senac.demo.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {
    public final static String BUSCAR_POR_EMAIL = "SELECT * FROM TB_USER WHERE EMAIL LIKE :email";

    @Query(value = BUSCAR_POR_EMAIL, nativeQuery = true)
    public UserModel findByEmail(@Param("email") final String email);
}
