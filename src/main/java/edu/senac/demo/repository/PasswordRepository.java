package edu.senac.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import edu.senac.demo.model.SenhaModel;

public interface PasswordRepository extends JpaRepository<SenhaModel, Integer>, JpaSpecificationExecutor<SenhaModel> {

    public final static String BUSCAR_SENHAS_USER = "SELECT * FROM TB_SENHA WHERE ID_USER = :idUser";

    @Query(value = BUSCAR_SENHAS_USER, nativeQuery = true)
    public List<SenhaModel> findSenhasByIdUser(@Param("idUser") final Integer idUser);
}
