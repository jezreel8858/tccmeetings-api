package com.br.unipe.tccmeetings.user;

import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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

	@NotNull
	@Size(min = 4, max = 80)
	@Column(name = "name", length = 80, nullable = false)
	private String name;

	@Email
	@NotNull
	@NotEmpty
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;

	@NotNull
	@Size(min = 80, max = 80)
	@Column(name = "password", length = 80, nullable = false)
	@JsonProperty("password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_permission", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id") )
	private List<PermissionEntity> permissions;

	public UserEntity() {
	}

	public UserEntity(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}


}
