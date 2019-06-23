/**
 * 
 */
package br.com.cams7.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.cams7.app.View;
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
@ApiModel(description = "Classe que representa uma função (ROLE).")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "tb_funcao", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
public class RoleEntity {

	@ApiModelProperty(notes = "Identificador unico da função (ROLE).", example = "1", required = true, position = 0)
	@JsonView(View.Public.class)
	@Id
	@SequenceGenerator(name = "sq_funcao", sequenceName = "sq_funcao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_funcao")
	@Column(name = "id_funcao")
	private Long id;

	@ApiModelProperty(notes = "Nome da função (ROLE).", example = "ROLE_USER", required = true, position = 1)
	@JsonView(View.Public.class)
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "nome", length = 30)
	private RoleName name;

	@ApiModelProperty(notes = "Listagem com os usuários que tem essa função (ROLE).", required = false, position = 2)
	@JsonView(View.Public.class)
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<UserEntity> users;

	public static enum RoleName {
		ROLE_USER, ROLE_PM, ROLE_ADMIN
	}

}
