/**
 * 
 */
package br.com.cams7.app.audit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.cams7.app.View;
import br.com.cams7.app.model.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ceanm
 *
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

	@ApiModelProperty(notes = "Usuário que criou essa entidade.", required = false, position = 0)
	@JsonView(View.Public.class)
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "criado_por", nullable = true)
	private UserEntity createdBy;

	@ApiModelProperty(notes = "Data de criação dessa entidade.", required = false, position = 1)
	@JsonView(View.Public.class)
	@CreatedDate
	@Column(name = "data_criacao", nullable = true)
	private LocalDateTime createdDate;

	@ApiModelProperty(notes = "Usuário que alterou essa entidade.", required = false, position = 2)
	@JsonView(View.Public.class)
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modificado_por", nullable = true)
	private UserEntity lastModifiedBy;

	@ApiModelProperty(notes = "Data de alteração dessa entidade.", required = false, position = 3)
	@JsonView(View.Public.class)
	@LastModifiedDate
	@Column(name = "data_alteracao", nullable = true)
	private LocalDateTime lastModifiedDate;
}
