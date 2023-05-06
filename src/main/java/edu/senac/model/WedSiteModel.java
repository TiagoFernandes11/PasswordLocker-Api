package edu.senac.model;

import javax.persistence.*;
//import javax.validation.constraints.*;

@Entity
@Table(name = "TB_WEBSITE")
public class WedSiteModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_website")
    private int idWebsite;

    @Column(name = "linked_url", length = 50, nullable = false)
    private String linkedUrl;

    @Column(name = "linked_username", length = 50, nullable = false)
    private String linkedUsername;

}
