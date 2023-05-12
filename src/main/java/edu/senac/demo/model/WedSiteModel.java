package edu.senac.demo.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_WEBSITE")
public class WedSiteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_WEBSITE")
    private int idWebsite;

    @Column(name = "URL", length = 500, nullable = false)
    private String UrlSite;

    @Column(name = "USUARIO", length = 100, nullable = false)
    private String Usuario;

    @Column(name = "SENHA", length = 100, nullable = false)
    private String senha;

    @Column(name = "ID_USER")
    private int fk_idUser;

}
