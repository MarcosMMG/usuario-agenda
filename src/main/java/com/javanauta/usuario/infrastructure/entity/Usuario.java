package com.javanauta.usuario.infrastructure.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//Gerar automaticamento os GET's.
@Getter
//Gerar automaticamento os SET's
@Setter
//Gerar automaticamento os todos os construtores
@AllArgsConstructor
//Gerar automaticamento os contrutores vazios
@NoArgsConstructor
//Apontamento para o nosso spring que e uma tabela do BD.
@Entity
//Indicar o nome da nossa tabela.
@Table(name = "usuario")

public class Usuario implements UserDetails {

    //Indicador unico da nossa tabela seria o PRIMARY KEY no SQL.
    @Id
    // esse gera o indicador(ID) automaticamente.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Column faz indicação o nome da nossa coluna
    @Column(name = "nome",length = 100)
    private String nome;
    @Column(name = "email",length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;
    // Essa anota se refere 1 usuario para muitiplos endereço. Caso foss @OneToOne seria 1 usuario para 1 endereço
    @OneToMany(cascade = CascadeType.ALL)
    /*Essa anotação define a coluna que será usada como chave estrangeira na tabela, referenciando a
     coluna 'id' da tabela associada.*/
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> endereco;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuaria_id", referencedColumnName = "id")
    private List<Telefone> telefone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
