package com.br.unipe.tccmeetings.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    @Query("SELECT p FROM PermissionEntity p WHERE p.role = 'ROLE_DOCENTE'")
    List<PermissionEntity> findPermissionByDocente();

    @Query("SELECT p FROM PermissionEntity p WHERE p.role = 'ROLE_DISCENTE'")
    List<PermissionEntity> findPermissionByDiscente();
}
