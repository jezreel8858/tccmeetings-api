package com.br.unipe.tccmeetings.user;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.apache.catalina.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.br.unipe.tccmeetings.reuniao.ReuniaoEntity;
import com.br.unipe.tccmeetings.utils.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_user")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class UserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201602010251L;

	@JsonView(UserEntity.Views.Public.class)
	@NotNull
	@Size(min = 4, max = 80)
	@Column(name = "name", length = 80, nullable = false)
	private String name;

	@JsonView(UserEntity.Views.Internal.class)
	@Email
	@NotNull
	@NotEmpty
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;

	@JsonView(UserEntity.Views.Internal.class)
	@NotNull
	@Size(min = 80, max = 80)
	@Column(name = "password", length = 80, nullable = false)
	@JsonProperty("password")
	private String password;

	@JsonView(UserEntity.Views.Internal.class)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_permission", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id") )
	private List<PermissionEntity> permissions;

	@Null
	@JsonView(UserEntity.Views.Internal.class)
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ReuniaoEntity> reunioes;

	public UserEntity() {
	}

	public UserEntity(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public static final class Views {
		// show only public data
		public interface Public {}

		// show public and internal data
		public interface Internal extends UserEntity.Views.Public {}
	}


}
