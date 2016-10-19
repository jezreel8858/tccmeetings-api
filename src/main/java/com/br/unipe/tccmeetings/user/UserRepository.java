package com.br.unipe.tccmeetings.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	List<UserEntity> findByEmailOrName(String email, String name);

}
