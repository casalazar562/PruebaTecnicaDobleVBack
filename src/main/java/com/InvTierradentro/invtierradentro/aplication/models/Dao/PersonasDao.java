package com.InvTierradentro.invtierradentro.aplication.models.Dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="personas")
public class PersonasDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona")
    private Long IdUser;

    @Column(length = 255)
    private String names;

    @Column(length = 255)
    private String last_names;

    @Column(length = 10)
    private String identification;

    @Column(unique=true,length = 255)
    private String email;

   @Column ( name="TipoId",length=2)
    private String TipoId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(length = 255)
    private String names_column;

    @Column(length = 255)
    private String identification_column;



    private static final long serialVersionUID = 4012221612401133094L;
}
