package edu.senac.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import edu.senac.demo.model.SenhaModel;

public interface PasswordRepository extends JpaRepository<SenhaModel, String>, JpaSpecificationExecutor<SenhaModel> {

    public final static String FIND_USER_PASSWORDS = "SELECT * FROM TB_SENHA WHERE ID_USER LIKE :idUser";
    public final static String FIND_BY_ID = "SELECT * FROM TB_SENHA WHERE ID_SENHA LIKE :idSenha";
    public final static String UPDATE_PASSWORD = "UPDATE TB_SENHA SET TITULO SENHA DATA_ALTERACAO ";
<<<<<<< HEAD
    public final static String DELETE_BY_ID = "DELETE FROM TB_SENHA WHERE ID_SENHA LIKE :idSenha";
=======
    public final static String DELETE_PASSWORD_BY_ID = "DELETE FROM TB_SENHA WHERE ID_SENHA LIKE :idSenha";
>>>>>>> main

    @Query(value = FIND_USER_PASSWORDS, nativeQuery = true)
    public List<SenhaModel> findSenhasByIdUser(@Param("idUser") final String idUser);

    @Query(value = FIND_BY_ID, nativeQuery = true)
    public SenhaModel findByGuidId(@Param("idSenha") final String idSenha);

<<<<<<< HEAD
    // @Query(value = UPDATE_PASSWORD, nativeQuery = true)
     //public boolean update
=======
    @Query(value = DELETE_PASSWORD_BY_ID, nativeQuery = true)
    public boolean deleteByGuidId(@Param("idSenha") final String idSenha);
>>>>>>> main

   /* @Query(value = DELETE_BY_ID, nativeQuery = true)
    public boolean deleteByGuidId(@Param("idSenha") final String idSenha);
*/
    @Query(value = DELETE_BY_ID, nativeQuery = true)
    public static boolean deleteByGuidId(@Param("idSenha") final String idSenha) {
        return false;
    }
}
