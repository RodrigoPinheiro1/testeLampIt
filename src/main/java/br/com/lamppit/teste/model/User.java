package br.com.lamppit.teste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_system", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 4358313336091674789L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, message = "{field.size}")
	@NotNull(message = "{field.notnull}")
	@NotEmpty(message = "{field.notempty}")
	@Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$", message = "{field.pattern.notNumber}")
	private String name;

	@Email(message = "{field.email}")
	@NotNull(message = "{field.notnull}")
	private String email;

	@NotNull(message = "{field.notnull}")
	@NotEmpty(message = "{field.notempty}")
	private String username;

	@NotNull(message = "{field.notnull}")
	private String password;



}
