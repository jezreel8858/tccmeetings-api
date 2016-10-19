package com.br.unipe.tccmeetings.curso;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jezreel on 13/10/16.
 */
public interface CursoRepository extends JpaRepository<CursoEntity,Long> {

    CursoEntity findById(Long id);
}
