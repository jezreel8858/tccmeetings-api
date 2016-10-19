package com.br.unipe.tccmeetings.userpermission;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.br.unipe.tccmeetings.utils.BaseEntity;

@Entity
@Table(name = "tb_user_permission")
public class UserPermissionEntity extends BaseEntity<UserPermissionKey> {

	private static final long serialVersionUID = 201602010251L;

}