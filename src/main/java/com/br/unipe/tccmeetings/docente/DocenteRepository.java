package com.br.unipe.tccmeetings.docente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jezreel on 10/10/16.
 */
public interface DocenteRepository extends JpaRepository<DocenteEntity,Long> {

    @Query("SELECT d FROM DocenteEntity d WHERE LOWER(d.name) LIKE %:content%")
    List<DocenteEntity> findByNome(@Param("content") String content);

    DocenteEntity findById(Long id);

    DocenteEntity findByEmail(String email);
}
