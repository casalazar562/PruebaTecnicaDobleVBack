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
@Table(name="roles")
public class RoleDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDROL")
    private Long idRol;

    @Column(name="DESCRIPTION", unique = true)
    private String DESCRIPTION;



    private static final long serialVersionUID = 4012221612401133094L;


}
