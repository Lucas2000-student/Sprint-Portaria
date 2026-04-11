package br.com.fiap.Portaria.entity;

import br.com.fiap.Portaria.dto.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TPL_MORADOR")
public class Morador {

    @Id
    private Integer idMorador;

    private String nome;
    private String contato;

    @ManyToOne
    @JoinColumn(name = "ID_APARTAMENTO")
    private Apartamento apartamento;

    @OneToMany(mappedBy = "morador")
    private List<Encomenda> encomendas;

    @OneToMany(mappedBy = "morador")
    private List<Retirada> retiradas;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN ou MORADOR

    @Column(name = "FIREBASE_UID", unique = true)
    private String firebaseUid;

    private String telefone;

}
