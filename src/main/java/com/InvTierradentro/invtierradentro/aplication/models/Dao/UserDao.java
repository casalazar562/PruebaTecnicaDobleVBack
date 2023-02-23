package com.InvTierradentro.invtierradentro.aplication.models.Dao;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")
public class UserDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long IdUser;

    @Column(unique = true,length = 20)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name="status")
    private Boolean status;


    @ManyToOne
    @JoinColumn(name = "id_persona")
    private  PersonasDao personasDao;



    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="RoleUser",joinColumns =@JoinColumn(name="IdUser"), inverseJoinColumns = @JoinColumn(name="idRol"), uniqueConstraints = {@UniqueConstraint(columnNames = {"IdUser","idRol"})})
    private List<RoleDao> roles;





    private static final long serialVersionUID = 4012221612401133094L;

}
