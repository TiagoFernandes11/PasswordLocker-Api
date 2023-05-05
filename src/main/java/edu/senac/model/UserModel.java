package edu.senac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome é obrigatório!")
    @Size(min=2, message="O nome deve, no mínimo, ter 3 caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Email(message="Insira um e-mail válido!")
    @NotEmpty(message = "O e-mail é obrigatório!")
    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @CPF
    @NotEmpty(message = "O CPF é obrigatório!")
    @Column(name = "cpf", length = 11)
    private String cpf;

    @Size(min=5, message="A senha deve, no mínimo, ter 5 caracteres")
    @NotEmpty(message = "A senha é obrigatória!")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @NotEmpty(message = "O numero de telefone é obrigatória!")
    @Column(name = "telefone", nullable = false)
    private String telefone;

}
