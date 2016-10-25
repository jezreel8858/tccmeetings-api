package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.docente.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by jezreel on 10/10/16.
 */
public interface DiscenteRepository extends JpaRepository<DiscenteEntity,Long> {

    DiscenteEntity findByEmail(String email);

    @Query("SELECT d FROM DiscenteEntity d WHERE d.id = :id")
    DiscenteEntity findById(@Param("id") Long id);

    DocenteEntity   findByDocenteId(@Param("id")Long id);
}
