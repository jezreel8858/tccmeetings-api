package com.br.unipe.tccmeetings.disciplina;

import com.br.unipe.tccmeetings.docente.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jezreel on 13/10/16.
 */
public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {

    @Query("SELECT d FROM DisciplinaEntity d WHERE d.id = :id")
    DisciplinaEntity findByPk_id(@Param("id") Long id);

    @Query("SELECT d FROM DisciplinaEntity d WHERE LOWER(d.nome) LIKE %:content%")
    List<DisciplinaEntity> findByNome(@Param("content") String content);

}
