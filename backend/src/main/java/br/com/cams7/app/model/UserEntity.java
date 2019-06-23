/**
 * 
 */
package br.com.cams7.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.cams7.app.View;
import br.com.cams7.app.audit.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ceanm
 *
 */
@ApiModel(description = "Classe que representa um usuário.")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@NamedEntityGraph(name = UserEntity.LOAD_ROLES, attributeNodes = { @NamedAttributeNode("roles") })
@Entity
@Table(name = "tb_usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "login_usuario" }),
		@UniqueConstraint(columnNames = { "email" }) })
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends Auditable {

	public static final String LOAD_ROLES = "User.loadRoles";

	@ApiModelProperty(notes = "Identificador unico do usuário.", example = "1", required = true, position = 4)
	@JsonView(View.Public.class)
	@Id
	@SequenceGenerator(name = "sq_usuario", sequenceName = "sq_usuario", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario")
	@Column(name = "id_usuario")
	private Long id;

	@ApiModelProperty(notes = "Nome do usuário.", example = "José Silva Pereira", required = true, position = 5)
	@JsonView(View.Public.class)
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "nome")
	private String name;

	@ApiModelProperty(notes = "Login de identificação do usuário.", example = "jose2018", required = true, position = 6)
	@JsonView(View.Public.class)
	@NotBlank
	@Size(min = 3, max = 30)
	@Column(name = "login_usuario")
	private String username;

	@ApiModelProperty(notes = "E-mail do usuário.", example = "jose@teste.com", required = true, position = 7)
	@JsonView(View.Public.class)
	@NotBlank
	@Size(min = 5, max = 50)
	@Email
	@NaturalId
	private String email;

	@ApiModelProperty(notes = "Senha criptografada do usuário.", example = "M!nh@S&nh@2019", required = true, position = 8)
	@JsonView(View.LoggedIn.class)
	@NotBlank
	@Size(min = 6, max = 100)
	@Column(name = "senha")
	private String password;

	@ApiModelProperty(notes = "Listagem com as funções (ROLES) do usuário.", required = false, position = 9)
	@JsonView(View.Public.class)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_usuario_funcao", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_funcao") })
	private Set<RoleEntity> roles;

}
