package edu.senac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TB_SENHA")
public class SenhaModel  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSenha;

    @NotEmpty(message = "A senha é obrigatório!")
    @Column(name = "nome", length = 100, nullable = false)
    private String senha;

    @Column(name = "password_level", length = 50, nullable = false)
    private String idUser;

}
