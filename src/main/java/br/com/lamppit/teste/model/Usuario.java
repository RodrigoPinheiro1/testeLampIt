package br.com.lamppit.teste.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


//@Table(name = "user_system", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    private static final long serialVersionUID = 4358313336091674789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Size(min = 3, message = "{field.size}")
//    @NotNull(message = "{field.notnull}")
//    @NotEmpty(message = "{field.notempty}")
//    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$", message = "{field.pattern.notNumber}")
    private String nome;

//    @Email(message = "{field.email}")
//    @NotNull(message = "{field.notnull}")
    private String email;

   // @NotNull(message = "{field.notnull}")
    //@NotEmpty(message = "{field.notempty}")
    private String username;

    //@NotNull(message = "{field.notnull}")
    private String password;


//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Perfil> perfils = new ArrayList<>();
//

}
