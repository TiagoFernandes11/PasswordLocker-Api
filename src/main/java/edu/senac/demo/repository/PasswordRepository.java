package edu.senac.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import edu.senac.demo.model.PasswordModel;

public interface PasswordRepository
        extends JpaRepository<PasswordModel, String>, JpaSpecificationExecutor<PasswordModel> {

    public final static String FIND_USER_PASSWORDS = "SELECT * FROM TB_SENHA WHERE ID_USER LIKE :idUser";
    public final static String FIND_BY_ID = "SELECT * FROM TB_SENHA WHERE ID_SENHA LIKE :idSenha";
    public final static String UPDATE_PASSWORD = "UPDATE TB_SENHA SET TITULO SENHA DATA_ALTERACAO ";
    public final static String DELETE_PASSWORD_BY_ID = "DELETE FROM TB_SENHA WHERE ID_SENHA LIKE :idSenha";

    @Query(value = FIND_USER_PASSWORDS, nativeQuery = true)
    public List<PasswordModel> findSenhasByIdUser(@Param("idUser") final String idUser);

    @Query(value = FIND_BY_ID, nativeQuery = true)
    public PasswordModel findByGuidId(@Param("idSenha") final String idSenha);

    @Query(value = DELETE_PASSWORD_BY_ID, nativeQuery = true)
    public PasswordModel deleteByGuidId(@Param("idSenha") final String idSenha);

}
