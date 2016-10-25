package com.br.unipe.tccmeetings.docente;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
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

    @Query("SELECT di FROM DocenteEntity do INNER JOIN do.discentes di WHERE do.id = :id")
    List<DiscenteEntity> findAllDiscentes(@Param("id")Long id);

    @Query("SELECT di,r FROM DocenteEntity do INNER JOIN do.reunioes r INNER JOIN do.discentes di WHERE do.id = :id")
    List<DiscenteEntity> findAllDiscentesReunioes(@Param("id")Long id);
}
