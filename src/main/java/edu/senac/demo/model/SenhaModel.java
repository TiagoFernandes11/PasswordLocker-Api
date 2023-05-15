package edu.senac.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SENHA")
public class SenhaModel implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID_SENHA")
    private String idSenha;

    @NotEmpty(message = "A senha é obrigatória!")
    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;

    @Column(name = "SENHA", length = 50, nullable = false)
    private String senha;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private Date dataCriacao;

    @Column(name = "DATA_ALTERACAO", nullable = true)
    private Date dataAlteracao;

    @Column(name = "ID_USER")
    private int fk_idUser;

}
