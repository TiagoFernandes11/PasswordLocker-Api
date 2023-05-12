package edu.senac.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_SENHA")
public class SenhaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SENHA")
    private int idSenha;

    @NotEmpty(message = "A senha é obrigatória!")
    @Column(name = "PASSWORD_LEVEL", length = 100, nullable = false)
    private String passwordLevel;

    @Column(name = "SENHA", length = 50, nullable = false)
    private String senha;

    @Column(name = "ID_USER")
    private int fk_idUser;

}
